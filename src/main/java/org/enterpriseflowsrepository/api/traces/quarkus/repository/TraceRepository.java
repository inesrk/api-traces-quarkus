package org.enterpriseflowsrepository.api.traces.quarkus.repository;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.InternalServerErrorException;

import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.elasticsearch.ElasticsearchStatusException;
import org.elasticsearch.action.bulk.BulkItemResponse;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.indices.CreateIndexResponse;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.sort.FieldSortBuilder;
import org.elasticsearch.search.sort.SortBuilder;
import org.elasticsearch.search.sort.SortOrder;
import org.enterpriseflowsrepository.api.traces.quarkus.clients.elasticsearch.HttpClient;
import org.enterpriseflowsrepository.api.traces.quarkus.model.TraceModel;

@ApplicationScoped
public class TraceRepository {

    /**
     * Shared logger of this class.
     */
    private static final Logger LOG = Logger.getLogger(TraceRepository.class.getName());

    /**
     * The referenced Elasticsearch index.
     * <p>
     * Configured in the configuration file.
     */
    @ConfigProperty(name = "traces.db.elasticsearch.index")
    String elasticsearchIndex;

    /**
     * The shared Elasticsearch HTTP client.
     */
    @Inject
    HttpClient elasticClient;

    /**
     * Test and initialize if needed the Elasticsearch cluster to receive data.
     * <p>
     * Test if the index is properly created. If not the index is created.
     */
    @PostConstruct
    void initialization() {
        // test if index exists or not
        boolean isExistsIndex = false;
        try {
            isExistsIndex = elasticClient.existsIndexAsync(elasticsearchIndex).get();
        } catch (ElasticsearchStatusException e) {
            throw new InternalServerErrorException(e);
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }

        // is index already exists, abort
        if (isExistsIndex) {
            LOG.fine(String.format("Index '%s' exists, let's fire!", elasticsearchIndex));
            return;
        }

        LOG.fine(String.format("Index '%s' does not exists already, crating it", elasticsearchIndex));

        // if not found on the db, create index
        CreateIndexResponse res = null;
        try {
            res = elasticClient.createIndexAsync(elasticsearchIndex).get();
        } catch (ElasticsearchStatusException e) {
            throw new InternalServerErrorException(e);
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }

        LOG.fine(String.format("Index '%s' has been successfully created", res.index()));
    }

    /**
     * Insert a list of Trace objects.
     * 
     * @param datas Trace collection
     */
    public void create(List<TraceModel> datas) {
        BulkResponse res = null;
        try {
            res = elasticClient.createAsync(elasticsearchIndex, datas).get();
        } catch (ElasticsearchStatusException e) {
            throw new InternalServerErrorException(e);
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }

        LOG.fine(String.format("Created bulk documents within index '%s' with ids '%s'", elasticsearchIndex,
                Arrays.stream(res.getItems()).map(BulkItemResponse::getId).collect(Collectors.joining(", "))));
    }

    /**
     * Search for Trace objects.
     * 
     * @param after     Returned objects will have be created after or at this exact date
     * @param before    Returned objects will have be created before or at this exact date
     * @param keys      Returned objects will contains this set of key
     * @return          Trace objects from the database
     */
    public List<TraceModel> search(Date after, Date before, List<String> keys) {
        BoolQueryBuilder query = QueryBuilders.boolQuery()
            // apply a date filter
            .filter(QueryBuilders.rangeQuery("created").gte(after).lte(before));

        // apply keys filter, if provided
        if(keys != null && !keys.isEmpty()) {
            query = query.filter(QueryBuilders.termsQuery("business.name.keyword", keys));
        }

        // we will sort results by creation date
        SortBuilder<FieldSortBuilder> sort = new FieldSortBuilder("created").order(SortOrder.ASC);

        List<TraceModel> models = null;
        try {
            @SuppressWarnings("unchecked")
            List<TraceModel> modelsTmp = (List<TraceModel>) elasticClient
                    .searchAsync(elasticsearchIndex, query, sort, TraceModel.class).get();
            models = modelsTmp;
        } catch (ElasticsearchStatusException e) {
            throw new InternalServerErrorException(e);
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }

        LOG.fine(String.format("Search document within index '%s' from '%s' to '%s' and keys '%s'", elasticsearchIndex,
                after, before, keys));

        return models;
    }

    /**
     * Insert a specific Trace object.
     * 
     * @param data  Trace object
     */
    public void create(TraceModel data) {
        IndexResponse res = null;
        try {
            res = elasticClient.createAsync(elasticsearchIndex, data).get();
        } catch (ElasticsearchStatusException e) {
            throw new InternalServerErrorException(e);
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }

        LOG.fine(String.format("Created document within index '%s' with id '%s'", elasticsearchIndex, res.getId()));
    }
}
