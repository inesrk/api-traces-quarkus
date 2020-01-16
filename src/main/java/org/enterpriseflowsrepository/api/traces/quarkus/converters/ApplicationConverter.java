package org.enterpriseflowsrepository.api.traces.quarkus.converters;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.ClientErrorException;
import org.enterpriseflowsrepository.api.traces.quarkus.beans.Application;
import org.enterpriseflowsrepository.api.traces.quarkus.model.ApplicationModel;

@ApplicationScoped
public class ApplicationConverter extends AbstractConverter<ApplicationModel, Application> {

  @Override
  public Application toDto(ApplicationModel model) throws ClientErrorException {
    if (model == null) {
      return null;
    }

    Application dto = new Application();

    dto.setApplication(model.getApplication());
    dto.setCreated(model.getCreated());
    dto.setHostname(model.getHostname());
    dto.setInformationSystem(model.getInformationSystem());

    return dto;
  }

  @Override
  public Application toDtoWithLinks(ApplicationModel model) {
    Application dto = toDto(model);

    // nothing needed here

    return dto;
  }

  @Override
  public ApplicationModel toModel(Application dto) {
    if (dto == null) {
      return null;
    }

    ApplicationModel model = new ApplicationModel();

    model.setApplication(dto.getApplication());
    model.setCreated(dto.getCreated());
    model.setHostname(dto.getHostname());
    model.setInformationSystem(dto.getInformationSystem());

    return model;
  }
}
