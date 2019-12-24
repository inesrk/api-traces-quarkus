package org.enterpriseflowsrepository.api.traces.quarkus.repository;

import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.elasticsearch.ElasticsearchStatusException;
import org.elasticsearch.action.bulk.BulkItemResponse;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.indices.CreateIndexResponse;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.sort.FieldSortBuilder;
import org.elasticsearch.search.sort.SortBuilder;
import org.elasticsearch.search.sort.SortOrder;
import org.enterpriseflowsrepository.api.traces.quarkus.model.TraceModel;
import org.enterpriseflowsrepository.api.traces.quarkus.clients.elasticsearch.HttpClient;

import java.util.Arrays;
import java.util.Date;
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

    @ConfigProperty(name = "traces.db.elasticsearch.index")
    String elasticsearchIndex;

    @Inject
    HttpClient elasticClient;

    @PostConstruct
    void initialization() {
        // test if index exists or not
        boolean isExistsIndex = false;
        try {
            isExistsIndex = elasticClient.existsIndexAsync(elasticsearchIndex).get();
        } catch (ElasticsearchStatusException e) {
            throw new RuntimeException(e);
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
            throw new RuntimeException(e);
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }

        LOG.fine(String.format("Index '%s' has been successfully created", res.index()));
    }

    public void create(List<TraceModel> datas) {
        BulkResponse res = null;
        try {
            res = elasticClient.createAsync(elasticsearchIndex, datas).get();
        } catch (ElasticsearchStatusException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }

        LOG.fine(String.format("Created bulk documents within index '%s' with ids '%s'", elasticsearchIndex,
                Arrays.stream(res.getItems()).map(BulkItemResponse::getId).collect(Collectors.joining(", "))));
    }

    public List<TraceModel> search(Date after, Date before, List<String> keys) {
        QueryBuilder query = (QueryBuilder) QueryBuilders.boolQuery()
                .filter(QueryBuilders.termsQuery("business.name.keyword", keys))
                .filter(QueryBuilders.rangeQuery("updated").gte(after).lte(before));
        SortBuilder<FieldSortBuilder> sort = new FieldSortBuilder("created").order(SortOrder.ASC);

        List<TraceModel> models = null;
        try {
            @SuppressWarnings("unchecked")
            List<TraceModel> modelsTmp = (List<TraceModel>) elasticClient
                    .searchAsync(elasticsearchIndex, query, sort, TraceModel.class).get();
            models = modelsTmp;
        } catch (ElasticsearchStatusException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }

        LOG.fine(String.format("Search document within index '%s' from '%s' to '%s' and keys '%s'", elasticsearchIndex,
                after, before, keys));

        return models;
    }

    public void create(TraceModel data) {
        IndexResponse res = null;
        try {
            res = elasticClient.createAsync(elasticsearchIndex, data).get();
        } catch (ElasticsearchStatusException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }

        LOG.fine(String.format("Created document within index '%s' with id '%s'", elasticsearchIndex, res.getId()));
    }

    public TraceModel getById(String id) {
        TraceModel model = null;
        try {
            model = (TraceModel) elasticClient.findAsync(elasticsearchIndex, id, TraceModel.class, false).get();
        } catch (ElasticsearchStatusException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }

        LOG.fine(String.format("Get document within index '%s' with id '%s'", elasticsearchIndex, id));

        return model;
    }
}
