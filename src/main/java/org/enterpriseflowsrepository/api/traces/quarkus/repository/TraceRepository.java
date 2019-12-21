package org.enterpriseflowsrepository.api.traces.quarkus.repository;

import org.enterpriseflowsrepository.api.traces.quarkus.beans.Trace;
import org.enterpriseflowsrepository.api.traces.quarkus.clients.elasticsearch.HttpClient;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;


@ApplicationScoped
public class TraceRepository {
    
    @Inject
    HttpClient elasticClient;
    
    public Trace getByKey(String id) {
        ObjectMapper mapper = new ObjectMapper();

        String json;
        try {
            json = elasticClient.findAsync("traces", id).get().getSourceAsString();
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }

        try {
            return mapper.readValue(json, Trace.class);
        } catch (JsonParseException | JsonMappingException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
