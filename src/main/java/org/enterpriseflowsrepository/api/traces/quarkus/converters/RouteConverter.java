package org.enterpriseflowsrepository.api.traces.quarkus.converters;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.ClientErrorException;
import org.enterpriseflowsrepository.api.traces.quarkus.beans.Route;
import org.enterpriseflowsrepository.api.traces.quarkus.model.RouteModel;

@ApplicationScoped
public class RouteConverter extends AbstractConverter<RouteModel, Route> {

  @Inject
  SourceConverter sourceConverter;

  @Override
  public Route toDto(RouteModel model) throws ClientErrorException {
    if (model == null) {
      return null;
    }

    Route dto = new Route();

    dto.setDescription(model.getDescription());
    dto.setId(model.getId());
    dto.setInput(sourceConverter.toDto(model.getInput()));
    dto.setName(model.getName());
    dto.setOutput(sourceConverter.toDto(model.getOutput()));
    dto.setStep(model.getStep());
    dto.setVersion(model.getVersion());

    return dto;
  }

  @Override
  public Route toDtoWithLinks(RouteModel model) {
    Route dto = toDto(model);

    // nothing needed here

    return dto;
  }

  @Override
  public RouteModel toModel(Route dto) {
    if (dto == null) {
      return null;
    }

    RouteModel model = new RouteModel();

    model.setDescription(dto.getDescription());
    model.setId(dto.getId());
    model.setInput(sourceConverter.toModel(dto.getInput()));
    model.setName(dto.getName());
    model.setOutput(sourceConverter.toModel(dto.getOutput()));
    model.setStep(dto.getStep());
    model.setVersion(dto.getVersion());

    return model;
  }
}
