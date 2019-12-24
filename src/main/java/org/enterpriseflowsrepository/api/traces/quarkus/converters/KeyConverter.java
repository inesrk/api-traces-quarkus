package org.enterpriseflowsrepository.api.traces.quarkus.converters;

import org.enterpriseflowsrepository.api.traces.quarkus.beans.Key;
import org.enterpriseflowsrepository.api.traces.quarkus.model.*;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.ClientErrorException;

@ApplicationScoped
public class KeyConverter extends AbstractConverter<KeyModel, Key> {

    @Override
    public Key toDto(KeyModel model) throws ClientErrorException {
        if (model == null) {
            return null;
        }

        Key dto = new Key();

        dto.setName(model.getName());
        dto.setValue(model.getValue());

        return dto;
    }

    @Override
    public Key toDtoWithLinks(KeyModel model) {
        Key dto = toDto(model);

        // nothing needed here

        return dto;
    }

    @Override
    public KeyModel toModel(Key dto) {
        if (dto == null) {
            return null;
        }

        KeyModel model = new KeyModel();

        model.setName(dto.getName());
        model.setValue(dto.getValue());

        return model;
    }
}
