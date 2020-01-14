package org.enterpriseflowsrepository.api.traces.quarkus.services;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.BadRequestException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.enterpriseflowsrepository.api.traces.quarkus.TraceResource;
import org.enterpriseflowsrepository.api.traces.quarkus.beans.Trace;
import org.enterpriseflowsrepository.api.traces.quarkus.converters.TraceConverter;
import org.enterpriseflowsrepository.api.traces.quarkus.model.TraceModel;
import org.enterpriseflowsrepository.api.traces.quarkus.repository.TraceRepository;
import org.enterpriseflowsrepository.api.traces.quarkus.utils.DateParameter;

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
        if(data == null) {
            throw new BadRequestException("data is empty");
        }

        traceRepo.create(traceConverter.toModel(data));

        return Response.status(Status.CREATED).build();
    }
    
    @Override
    public List<Trace> getTraces(DateParameter after, DateParameter before, List<String> keys) {
        if(after == null) {
            throw new BadRequestException("after is missing");
        }

        if(before == null) {
            throw new BadRequestException("before is missing");
        }

        if(after.getDate().after(before.getDate())) {
            throw new BadRequestException("after is greater than before");
        }
        
        List<TraceModel> models = traceRepo.search(after.getDate(), before.getDate(), keys);

        return traceConverter.toDto(models);
    }

    @Override
    public Response bulkTraces(List<Trace> data) {
        if(data == null || data.isEmpty()) {
            throw new BadRequestException("data is empty");
        }

        traceRepo.create(traceConverter.toModel(data));

        return Response.status(Status.CREATED).build();
    }
}
