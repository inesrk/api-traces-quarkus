package org.enterpriseflowsrepository.api.traces.quarkus.repository;

import org.elasticsearch.ElasticsearchStatusException;
import org.elasticsearch.action.DocWriteResponse;
import org.elasticsearch.action.bulk.BulkItemResponse;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.indices.CreateIndexResponse;
import org.enterpriseflowsrepository.api.traces.quarkus.model.TraceModel;
import org.enterpriseflowsrepository.api.traces.quarkus.clients.elasticsearch.HttpClient;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutionException;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import java.util.logging.Logger;
import java.util.stream.Collectors;


@ApplicationScoped
public class TraceRepository {

    /** Logger **/
    private static final Logger LOG = Logger.getLogger(TraceRepository.class.getName());

    private static final String ELASTICSEARCH_INDEX = "traces";

    @Inject
    HttpClient elasticClient;

    @PostConstruct
    void initialization() {
        // test if the index exists or not
        boolean isExistsIndex = false;
        try {
           isExistsIndex = elasticClient.existsIndexAsync(ELASTICSEARCH_INDEX).get(); 
        } catch (ElasticsearchStatusException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }

        // is the index already exists, abort
        if(isExistsIndex) {
            LOG.fine(String.format("The index '%s' already exists, abort creation", ELASTICSEARCH_INDEX));
            return;
        }

        LOG.fine(String.format("The index '%s' does not exists already, crating it", ELASTICSEARCH_INDEX));

        // if not found on the db, create the index
        CreateIndexResponse res = null;
        try {
            res = elasticClient.createIndexAsync(ELASTICSEARCH_INDEX).get();
        } catch (ElasticsearchStatusException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }

        LOG.fine(String.format("The index '%s' has been successfully created", res.index()));
    }

    public void create(List<TraceModel> datas) {
        BulkResponse res = null;
        try {
            res = elasticClient.createAsync(ELASTICSEARCH_INDEX, datas).get();
        } catch (ElasticsearchStatusException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }

        LOG.fine(String.format("Created bulk documents for index '%s' with ids '%s'", ELASTICSEARCH_INDEX, Arrays.stream(res.getItems()).map(BulkItemResponse::getId).collect(Collectors.joining(", "))));
    }

    public void create(TraceModel data) {
        IndexResponse res = null;
        try {
            res = elasticClient.createAsync(ELASTICSEARCH_INDEX, data).get();
        } catch (ElasticsearchStatusException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }

        LOG.fine(String.format("Created document for index '%s' with id '%s'", ELASTICSEARCH_INDEX, res.getId()));
    }
    
    public TraceModel getById(String id) {
        TraceModel model = null;
        try {
            model = (TraceModel) elasticClient.findAsync(ELASTICSEARCH_INDEX, id, TraceModel.class, false).get();
        } catch (ElasticsearchStatusException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }

        LOG.fine(String.format("Get document for index '%s' with id '%s'", ELASTICSEARCH_INDEX, id));

        return model;
    }
}
