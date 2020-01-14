package org.enterpriseflowsrepository.api.traces.quarkus.converters;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.ws.rs.ClientErrorException;

import org.enterpriseflowsrepository.api.traces.quarkus.services.AbstractService;

public abstract class AbstractConverter<ModelClass, DtoClass> {

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
     * @param model Source model
     * @return      A DTO
     * @throws ClientErrorException
     */
    abstract public DtoClass toDto(ModelClass model) throws ClientErrorException;

    /**
     * Convert a List of DTO in a List of Model
     * 
     * @param models    Collection of source models
     * @return          A DTO collection
     */
    public List<DtoClass> toDto(List<ModelClass> models) throws ClientErrorException {
        List<DtoClass> dtos = new ArrayList<DtoClass>();

        for (ModelClass model : models) {
            dtos.add(toDto(model));
        }

        return dtos;
    }

    abstract public DtoClass toDtoWithLinks(ModelClass model) throws ClientErrorException;

    /**
     * Convert a DTO to a model.
     * 
     * @param dto   Source DTO
     * @return      A model
     */
    abstract public ModelClass toModel(DtoClass dto) throws ClientErrorException;

    /**
     * Convert a List of DTO in a List of Model
     * 
     * @param dtos  Collection of source DTOs
     * @return      A model collection
     */
    public List<ModelClass> toModel(List<DtoClass> dtos) throws ClientErrorException {
        List<ModelClass> models = new ArrayList<ModelClass>();

        for (DtoClass dto : dtos) {
            models.add(toModel(dto));
        }

        return models;
    }
}
