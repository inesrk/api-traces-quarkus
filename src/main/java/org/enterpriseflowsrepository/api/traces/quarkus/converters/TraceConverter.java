package org.enterpriseflowsrepository.api.traces.quarkus.converters;

import org.enterpriseflowsrepository.api.traces.quarkus.beans.Trace;
import org.enterpriseflowsrepository.api.traces.quarkus.model.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.ClientErrorException;

@ApplicationScoped
public class TraceConverter extends AbstractConverter<TraceModel, Trace> {

    @Inject
    KeyConverter keyConverter;

    @Inject
    ExceptionConverter exceptionConverter;

    @Inject
    InfrastructureConverter infrastructureConverter;

    @Inject
    MessageConverter messageConverter;

    @Inject
    SourceConverter sourceConverter;

    @Inject
    RouteConverter routeConverter;

    @Override
    public Trace toDto(TraceModel model) throws ClientErrorException {
        if (model == null) {
            return null;
        }

        Trace dto = new Trace();

        dto.getBusiness().addAll(keyConverter.toDto(model.getBusiness()));
        dto.setEnvironnement(model.getEnvironnement());
        dto.setException(exceptionConverter.toDto(model.getException()));
        dto.setInfrastructure(infrastructureConverter.toDto(model.getInfrastructure()));
        dto.setMessage(messageConverter.toDto(model.getMessage()));
        dto.setOrigin(sourceConverter.toDto(model.getOrigin()));
        dto.setRoute(routeConverter.toDto(model.getRoute()));
        dto.setVersion(model.getVersion());
        dto.setId(model.getId());
        dto.setCreated(model.getCreated());
        dto.setUpdated(model.getUpdated());

        return dto;
    }

    @Override
    public Trace toDtoWithLinks(TraceModel model) {
        Trace dto = toDto(model);

        // nothing to do here

        return dto;
    }

    @Override
    public TraceModel toModel(Trace dto) {
        if (dto == null) {
            return null;
        }

        TraceModel model = new TraceModel();

        // no version and id here, these fields are only declarative from db to user
        model.getBusiness().addAll(keyConverter.toModel(dto.getBusiness()));
        model.setEnvironnement(dto.getEnvironnement());
        model.setException(exceptionConverter.toModel(dto.getException()));
        model.setInfrastructure(infrastructureConverter.toModel(dto.getInfrastructure()));
        model.setMessage(messageConverter.toModel(dto.getMessage()));
        model.setOrigin(sourceConverter.toModel(dto.getOrigin()));
        model.setRoute(routeConverter.toModel(dto.getRoute()));
        model.setCreated(dto.getCreated());
        model.setUpdated(dto.getUpdated());

        return model;
    }
}
