package org.enterpriseflowsrepository.api.traces.quarkus.clients.elasticsearch;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

import javax.enterprise.context.ApplicationScoped;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.apache.http.HttpHost;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.elasticsearch.action.ActionListener;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.support.WriteRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.CreateIndexResponse;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.sort.SortBuilder;

@ApplicationScoped
public class HttpClient {

    private static final Logger LOG = Logger.getLogger(HttpClient.class.getName());

    private RestHighLevelClient client;

    @ConfigProperty(name = "traces.db.elasticsearch.host")
    String elasticsearchHost;

    @ConfigProperty(name = "traces.db.elasticsearch.port")
    Integer elasticsearchPort;

    @ConfigProperty(name = "traces.db.elasticsearch.protocol")
    String elasticsearchProtocol;

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

    public CompletableFuture<List<? extends AbstractDocument>> searchAsync(String index, QueryBuilder query,
            SortBuilder<?> sort, Class<? extends AbstractDocument> source) {
        CompletableFuture<SearchResponse> future = new CompletableFuture<>();

        SearchRequest req = new SearchRequest();
        req.indices(index);
        SearchSourceBuilder reqBuilder = new SearchSourceBuilder();
        reqBuilder.timeout(new TimeValue(2, TimeUnit.MINUTES));
        reqBuilder.query(query);
        reqBuilder.sort(sort);
        req.source(reqBuilder);

        LOG.finest(req.source().toString());

        ActionListener<SearchResponse> listener = new ActionListener<SearchResponse>() {
            @Override
            public void onResponse(SearchResponse res) {
                future.complete(res);
            }

            @Override
            public void onFailure(Exception e) {
                future.completeExceptionally(e);
            }
        };

        getClient().searchAsync(req, RequestOptions.DEFAULT, listener);

        return future.thenApplyAsync((res) -> {
            List<AbstractDocument> datas = new ArrayList<>();
            SearchHits hits = res.getHits();
            SearchHit[] searchHits = hits.getHits();

            for (SearchHit hit : searchHits) {
                ObjectMapper jsonMapper = new ObjectMapper();
                String json = hit.getSourceAsString();

                AbstractDocument data = null;
                try {
                    data = jsonMapper.readValue(json, source);
                } catch (JsonParseException | JsonMappingException e) {
                    future.completeExceptionally(e);
                } catch (IOException e) {
                    future.completeExceptionally(e);
                }

                data.setId(hit.getId());
                data.setVersion(hit.getVersion());

                datas.add(data);
            }

            return datas;
        });
    }

    public CompletableFuture<BulkResponse> createAsync(String index, List<? extends AbstractDocument> datas) {
        CompletableFuture<BulkResponse> future = new CompletableFuture<>();
        ObjectMapper jsonMapper = new ObjectMapper();

        BulkRequest req = new BulkRequest();
        req.timeout(TimeValue.timeValueMinutes(2));
        req.setRefreshPolicy(WriteRequest.RefreshPolicy.WAIT_UNTIL);

        for (AbstractDocument data : datas) {
            String json = null;
            try {
                json = jsonMapper.writeValueAsString(data);
            } catch (JsonProcessingException e) {
                future.completeExceptionally(e);
            }

            req.add(new IndexRequest(index).source(json, XContentType.JSON));
        }

        ActionListener<BulkResponse> listener = new ActionListener<BulkResponse>() {
            @Override
            public void onResponse(BulkResponse res) {
                future.complete(res);
            }

            @Override
            public void onFailure(Exception e) {
                future.completeExceptionally(e);
            }
        };

        getClient().bulkAsync(req, RequestOptions.DEFAULT, listener);

        return future;
    }

    public CompletableFuture<IndexResponse> createAsync(String index, AbstractDocument data) {
        CompletableFuture<IndexResponse> future = new CompletableFuture<>();
        ObjectMapper jsonMapper = new ObjectMapper();

        String json = null;
        try {
            json = jsonMapper.writeValueAsString(data);
        } catch (JsonProcessingException e) {
            future.completeExceptionally(e);
        }

        IndexRequest req = new IndexRequest(index).source(json, XContentType.JSON);

        LOG.finest(req.source().toString());

        ActionListener<IndexResponse> listener = new ActionListener<IndexResponse>() {
            @Override
            public void onResponse(IndexResponse res) {
                future.complete(res);
            }

            @Override
            public void onFailure(Exception e) {
                future.completeExceptionally(e);
            }
        };

        getClient().indexAsync(req, RequestOptions.DEFAULT, listener);

        return future;
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

    public CompletableFuture<? extends AbstractDocument> findAsync(String index, String id,
            Class<? extends AbstractDocument> source, boolean forceRefresh) {
        CompletableFuture<GetResponse> future = new CompletableFuture<>();

        GetRequest req = new GetRequest(index, id);
        req.realtime(false);
        req.refresh(forceRefresh);

        ActionListener<GetResponse> listener = new ActionListener<GetResponse>() {
            @Override
            public void onResponse(GetResponse res) {
                future.complete(res);
            }

            @Override
            public void onFailure(Exception e) {
                future.completeExceptionally(e);
            }
        };

        getClient().getAsync(req, RequestOptions.DEFAULT, listener);

        return future.thenApplyAsync((res) -> {
            if (!res.isExists()) {
                return null;
            }

            ObjectMapper jsonMapper = new ObjectMapper();
            String json = res.getSourceAsString();

            AbstractDocument data = null;
            try {
                data = jsonMapper.readValue(json, source);
            } catch (JsonParseException | JsonMappingException e) {
                future.completeExceptionally(e);
            } catch (IOException e) {
                future.completeExceptionally(e);
            }

            data.setId(res.getId());
            data.setVersion(res.getVersion());

            return data;
        });
    }

    private RestHighLevelClient getClient() {
        if (client == null) {
            client = new RestHighLevelClient(RestClient.builder(new HttpHost(elasticsearchHost, elasticsearchPort, elasticsearchProtocol)));
        }

        return client;
    }
}
