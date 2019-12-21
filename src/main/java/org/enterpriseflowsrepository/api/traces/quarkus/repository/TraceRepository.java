package org.enterpriseflowsrepository.api.traces.quarkus.repository;

import org.elasticsearch.ElasticsearchStatusException;
import org.enterpriseflowsrepository.api.traces.quarkus.model.TraceModel;
import org.enterpriseflowsrepository.api.traces.quarkus.clients.elasticsearch.HttpClient;

import java.util.concurrent.ExecutionException;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import java.util.logging.Logger;


@ApplicationScoped
public class TraceRepository {

    /** Logger **/
    protected static final Logger LOG = Logger.getLogger(TraceRepository.class.getName());

    @Inject
    HttpClient elasticClient;

    @PostConstruct
    void initialization() {
        // test if the index exists or not
        boolean isExistsIndex = false;
        try {
           isExistsIndex = elasticClient.existsIndexAsync("traces").get(); 
        } catch (ElasticsearchStatusException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }

        // is the index already exists, abort
        if(isExistsIndex) return;

        // if not found on the db, create the index
        try {
            elasticClient.createIndexAsync("traces").get();
        } catch (ElasticsearchStatusException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
    }

    public TraceModel create(TraceModel data) {
        TraceModel stored = null;
        try {
            stored = (TraceModel) elasticClient.createAsync("traces", data).get();
        } catch (ElasticsearchStatusException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }

        return stored;
    }
    
    public TraceModel getById(String id) {
        try {
            return (TraceModel) elasticClient.findAsync("traces", id, TraceModel.class, false).get();
        } catch (ElasticsearchStatusException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
    }
}
