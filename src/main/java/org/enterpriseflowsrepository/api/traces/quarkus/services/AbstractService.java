package org.enterpriseflowsrepository.api.traces.quarkus.services;

import java.util.logging.Logger;


public abstract class AbstractService {

    /** Logger **/
    protected static final Logger LOG = Logger.getLogger(AbstractService.class.getName());
    
    /**
     * Default Constructor
     */
    public AbstractService() {
        //
    }
}
