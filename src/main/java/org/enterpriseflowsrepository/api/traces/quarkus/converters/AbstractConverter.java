package org.enterpriseflowsrepository.api.traces.quarkus.converters;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import javax.ws.rs.ClientErrorException;
import org.enterpriseflowsrepository.api.traces.quarkus.services.AbstractService;

public abstract class AbstractConverter<M, D> {

  protected static final Logger LOG = Logger.getLogger(AbstractService.class.getName());

  /**
   * Default constructor.
   */
  public AbstractConverter() {
    //
  }

  /**
   * Transform a model to a DTO.
   * 
   * @param model                 Source model
   * @return                      A DTO
   * @throws ClientErrorException If the trandformation failed
   */
  public abstract D toDto(M model) throws ClientErrorException;

  /**
   * Convert a List of DTO in a list of model.
   * 
   * @param models                Collection of source models
   * @return                      A DTO collection
   * @throws ClientErrorException If the trandformation failed
   */
  public List<D> toDto(List<M> models) throws ClientErrorException {
    List<D> dtos = new ArrayList<D>();

    for (M model : models) {
      dtos.add(toDto(model));
    }

    return dtos;
  }

  public abstract D toDtoWithLinks(M model) throws ClientErrorException;

  /**
   * Convert a DTO to a model.
   * 
   * @param dto                   Source DTO
   * @return                      A model
   * @throws ClientErrorException If the trandformation failed
   */
  public abstract M toModel(D dto) throws ClientErrorException;

  /**
   * Convert a List of DTO in a List of model.
   * 
   * @param dtos                  Collection of source DTOs
   * @return                      A model collection
   * @throws ClientErrorException If the trandformation failed
   */
  public List<M> toModel(List<D> dtos) throws ClientErrorException {
    List<M> models = new ArrayList<M>();

    for (D dto : dtos) {
      models.add(toModel(dto));
    }

    return models;
  }
}
