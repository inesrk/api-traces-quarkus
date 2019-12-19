package org.enterpriseflowsrepository.api.traces.quarkus.converters;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import org.enterpriseflowsrepository.api.traces.quarkus.services.AbstractService;


public abstract class AbstractConverter <ModelClass, DtoClass> {

    /** Logger **/
    protected static final Logger LOG = Logger.getLogger(AbstractService.class.getName());

    public AbstractConverter() {
        //
    }
    
    abstract public DtoClass toDto(ModelClass model) throws javax.ws.rs.ClientErrorException;
    
    /**
     * Convert a List of DTO in a List of Model
     * @param models
     * @return 
     */
    public List<DtoClass> toDto(List<ModelClass> models) throws javax.ws.rs.ClientErrorException {
        List<DtoClass> dtos = new ArrayList<DtoClass>();

        for(ModelClass model: models) {
            dtos.add(toDto(model));
        }
        
        return dtos;
    }

    abstract public DtoClass toDtoWithLinks(ModelClass model) throws javax.ws.rs.ClientErrorException;

    /**
     * Convert unique DTO to Model
     * @param dto
     * @return 
     */
    abstract public ModelClass toModel(DtoClass dto) throws javax.ws.rs.ClientErrorException;
    
    /**
     * Convert a List of DTO in a List of Model
     * @param dtos
     * @return 
     */
    public List<ModelClass> toModel(List<DtoClass> dtos)  throws javax.ws.rs.ClientErrorException {
        List<ModelClass> models = new ArrayList<ModelClass>();
        
        for(DtoClass dto: dtos) {
            models.add(toModel(dto));
        }
        
        return models;
    }
}
