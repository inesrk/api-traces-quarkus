package org.enterpriseflowsrepository.api.traces.quarkus.converters;

import org.enterpriseflowsrepository.api.traces.quarkus.beans.Exception;
import org.enterpriseflowsrepository.api.traces.quarkus.model.*;
import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.ClientErrorException;


@ApplicationScoped
public class ExceptionConverter extends AbstractConverter<ExceptionModel, Exception> {
    
    public ExceptionConverter() {
        super();
    }

    @Override
    public Exception toDto(ExceptionModel model) 
    throws ClientErrorException {
        if (model == null) {
            return null;
        }
        
        Exception dto = new Exception();

        dto.setClass_(model.getClass_());
        dto.setCode(model.getCode());
        dto.setDetail(model.getDetail());
        dto.setStacktrace(model.getStacktrace());
        
        return dto;
    }
    
    @Override
    public Exception toDtoWithLinks(ExceptionModel model) {
        Exception dto = toDto(model);
        
        // nothing needed here
        
        return dto;
    }

    @Override
    public ExceptionModel toModel(Exception dto) {
        if (dto == null) {
            return null;
        }
        
        ExceptionModel model = new ExceptionModel();
        
        model.setClass_(dto.getClass_());
        model.setCode(dto.getCode());
        model.setDetail(dto.getDetail());
        model.setStacktrace(dto.getStacktrace());

        return model;
    }
}
