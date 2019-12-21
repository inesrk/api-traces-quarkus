package org.enterpriseflowsrepository.api.traces.quarkus.clients.elasticsearch;

import java.io.IOException;
import java.util.concurrent.CompletableFuture;

import javax.enterprise.context.ApplicationScoped;

import org.apache.http.HttpHost;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;


@ApplicationScoped
public class HttpClient {
    
    private RestHighLevelClient client = new RestHighLevelClient(RestClient.builder(new HttpHost("localhost", 9200, "http")));

    HttpClient(RestHighLevelClient client) {
        this.client = client;
    }

    public CompletableFuture<GetResponse> findAsync(String index, String id) {
        return CompletableFuture.supplyAsync(() -> {
            GetRequest req = new GetRequest(index, id);
            req.realtime(false);
            req.refresh(true);

            try {
                return client.get(req, RequestOptions.DEFAULT);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }

    @Override
    protected void finalize() throws Throwable {
        client.close();
        super.finalize();
    }
}
