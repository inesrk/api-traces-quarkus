package org.enterpriseflowsrepository.api.traces.quarkus.clients.elasticsearch;

import java.io.IOException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.logging.Logger;

import javax.enterprise.context.ApplicationScoped;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.apache.http.HttpHost;
import org.elasticsearch.action.ActionListener;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.CreateIndexResponse;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.elasticsearch.common.xcontent.XContentType;

@ApplicationScoped
public class HttpClient {

    /** Logger **/
    protected static final Logger LOG = Logger.getLogger(HttpClient.class.getName());
    
    private RestHighLevelClient client;

    public CompletableFuture<Boolean> existsIndexAsync(String name) {
        CompletableFuture<Boolean> future = new CompletableFuture<>();

        GetIndexRequest req = new GetIndexRequest(name);
        req.local(false);
        req.includeDefaults(false);

        ActionListener<Boolean> listener = new ActionListener<Boolean>() {
            @Override
            public void onResponse(Boolean res) {
                future.complete(res);
            }

            @Override
            public void onFailure(Exception e) {
                future.completeExceptionally(e);
            }
        };

        getClient().indices().existsAsync(req, RequestOptions.DEFAULT, listener);

        return future;
    }

    public CompletableFuture<Object> createAsync(String index, Object data) {
        CompletableFuture<String> future = new CompletableFuture<>();
        ObjectMapper jsonMapper = new ObjectMapper();

        String json = null;
        try {
            json = jsonMapper.writeValueAsString(data);
        } catch (JsonProcessingException e) {
            future.completeExceptionally(e);
        }

        IndexRequest req = new IndexRequest(index).source(json, XContentType.JSON);

        ActionListener<IndexResponse> listener = new ActionListener<IndexResponse>() {
            @Override
            public void onResponse(IndexResponse res) {
                String storedId = res.getId();
                future.complete(storedId);
            }
        
            @Override
            public void onFailure(Exception e) {
                future.completeExceptionally(e);
            }
        };
        
        getClient().indexAsync(req, RequestOptions.DEFAULT, listener);

        return future.thenApplyAsync((storedId) -> {
            Object storedData = null;
            try {
                storedData = findAsync(index, storedId, data.getClass(), true).get();
            } catch (InterruptedException | ExecutionException e) {
                future.completeExceptionally(e);
            }

            return storedData;
        });
    }

    public CompletableFuture<CreateIndexResponse> createIndexAsync(String name) {
        CompletableFuture<CreateIndexResponse> future = new CompletableFuture<>();
        CreateIndexRequest req = new CreateIndexRequest(name);

        ActionListener<CreateIndexResponse> listener = new ActionListener<CreateIndexResponse>() {
            @Override
            public void onResponse(CreateIndexResponse res) {
                future.complete(res);
            }
        
            @Override
            public void onFailure(Exception e) {
                future.completeExceptionally(e);
            }
        };

        getClient().indices().createAsync(req, RequestOptions.DEFAULT, listener);

        return future;
    }

    public CompletableFuture<Object> findAsync(String index, String id, Class<? extends Object> source, boolean forceRefresh) {
        CompletableFuture<Object> future = new CompletableFuture<>();
        
        GetRequest req = new GetRequest(index, id);
        req.realtime(false);
        req.refresh(forceRefresh);

        ActionListener<GetResponse> listener = new ActionListener<GetResponse>() {
            @Override
            public void onResponse(GetResponse res) {
                ObjectMapper jsonMapper = new ObjectMapper();

                if(!res.isExists()) {
                    future.complete(null);
                }
        
                String json = res.getSourceAsString();
        
                Object data = null;
                try {
                    data = jsonMapper.readValue(json, source);
                } catch (JsonParseException | JsonMappingException e) {
                    future.completeExceptionally(e);
                } catch (IOException e) {
                    future.completeExceptionally(e);
                }

                if(data instanceof AbstractDocument) {
                    ((AbstractDocument) data).setId(res.getId());
                }

                future.complete(data);
            }
        
            @Override
            public void onFailure(Exception e) {
                future.completeExceptionally(e);
            }
        };

        getClient().getAsync(req, RequestOptions.DEFAULT, listener);
        
        return future;
    }

    /**
     * Get HTTP client
     */
    private RestHighLevelClient getClient() {
        if (client == null) {
            client = new RestHighLevelClient(RestClient.builder(new HttpHost("localhost", 9200, "http")));
        }

        return client;
    }
}
