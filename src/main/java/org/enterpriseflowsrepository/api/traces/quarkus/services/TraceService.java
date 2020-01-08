package org.enterpriseflowsrepository.api.traces.quarkus.services;

import java.util.List;

import org.enterpriseflowsrepository.api.traces.quarkus.beans.Trace;
import org.enterpriseflowsrepository.api.traces.quarkus.converters.TraceConverter;
import org.enterpriseflowsrepository.api.traces.quarkus.model.TraceModel;
import org.enterpriseflowsrepository.api.traces.quarkus.repository.TraceRepository;
import org.enterpriseflowsrepository.api.traces.quarkus.utils.DateParameter;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.core.Response;

import org.enterpriseflowsrepository.api.traces.quarkus.TraceResource;

@RequestScoped
public class TraceService extends AbstractService implements TraceResource {

    @Inject
    TraceConverter traceConverter;

    @Inject
    TraceRepository traceRepo;

    public TraceService() {
        super();
    }

    @Override
    public Response createTrace(Trace data) {
        traceRepo.create(traceConverter.toModel(data));
        return Response.status(Response.Status.CREATED).build();
    }
    
    @Override
    public List<Trace> getTraces(DateParameter after, DateParameter before, List<String> keys) {
        List<TraceModel> models = traceRepo.search(after.getDate(), before.getDate(), keys);

        return traceConverter.toDto(models);
    }

    @Override
    public Response bulkTraces(List<Trace> data) {
        traceRepo.create(traceConverter.toModel(data));
        return Response.status(Response.Status.CREATED).build();
    }
}
