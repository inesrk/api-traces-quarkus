package org.enterpriseflowsrepository.api.traces.quarkus.converters;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.ClientErrorException;

import org.enterpriseflowsrepository.api.traces.quarkus.beans.Source;
import org.enterpriseflowsrepository.api.traces.quarkus.model.SourceModel;

@ApplicationScoped
public class SourceConverter extends AbstractConverter<SourceModel, Source> {

    @Override
    public Source toDto(SourceModel model) throws ClientErrorException {
        if (model == null) {
            return null;
        }

        Source dto = new Source();

        dto.setProtocol(model.getProtocol());
        dto.setSource(model.getSource());

        return dto;
    }

    @Override
    public Source toDtoWithLinks(SourceModel model) {
        Source dto = toDto(model);

        // nothing needed here

        return dto;
    }

    @Override
    public SourceModel toModel(Source dto) {
        if (dto == null) {
            return null;
        }

        SourceModel model = new SourceModel();

        model.setProtocol(dto.getProtocol());
        model.setSource(dto.getSource());

        return model;
    }
}
