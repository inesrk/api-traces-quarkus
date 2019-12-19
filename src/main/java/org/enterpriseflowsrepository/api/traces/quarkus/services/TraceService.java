package org.enterpriseflowsrepository.api.traces.quarkus.services;

import java.util.Date;
import java.util.List;

import org.enterpriseflowsrepository.api.traces.quarkus.beans.Trace;
import org.enterpriseflowsrepository.api.traces.quarkus.converters.TraceConverter;
import org.enterpriseflowsrepository.api.traces.quarkus.repository.TraceRepository;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import org.enterpriseflowsrepository.api.traces.quarkus.TraceResource;


@RequestScoped
public class TraceService extends AbstractService implements TraceResource {

    @Inject
    TraceConverter converter;

    @Inject
    TraceRepository traceRepo;

    public TraceService() {
        super();
    }

    @Override
    public void createTrace(Trace data) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Trace getTrace(String name) {
        return traceRepo.getByKey(name);
    }

    @Override
    public void updateTrace(String name, Trace data) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void deleteTrace(String name) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<Trace> getTraces(Date after, Date before, List<String> keys) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void bulkTraces(List<Trace> data) {
        throw new UnsupportedOperationException("Not supported yet.");
    }   
}
