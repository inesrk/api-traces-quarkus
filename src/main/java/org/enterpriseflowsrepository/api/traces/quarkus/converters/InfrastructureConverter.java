package org.enterpriseflowsrepository.api.traces.quarkus.converters;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.ClientErrorException;
import org.enterpriseflowsrepository.api.traces.quarkus.beans.Infrastructure;
import org.enterpriseflowsrepository.api.traces.quarkus.model.InfrastructureModel;

@ApplicationScoped
public class InfrastructureConverter extends AbstractConverter<InfrastructureModel, Infrastructure> {

  @Override
  public Infrastructure toDto(InfrastructureModel model) throws ClientErrorException {
    if (model == null) {
      return null;
    }

    Infrastructure dto = new Infrastructure();

    dto.setHostname(model.getHostname());

    return dto;
  }

  @Override
  public Infrastructure toDtoWithLinks(InfrastructureModel model) {
    Infrastructure dto = toDto(model);

    // nothing needed here

    return dto;
  }

  @Override
  public InfrastructureModel toModel(Infrastructure dto) {
    if (dto == null) {
      return null;
    }

    InfrastructureModel model = new InfrastructureModel();

    model.setHostname(dto.getHostname());

    return model;
  }
}
