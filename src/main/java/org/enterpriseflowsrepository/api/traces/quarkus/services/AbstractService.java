package org.enterpriseflowsrepository.api.traces.quarkus.services;

import java.util.logging.Logger;

public abstract class AbstractService {

  /**
   * Shared logger of this class.
   */
  protected static final Logger LOG = Logger.getLogger(AbstractService.class.getName());

  /**
   * Default constructor.
   */
  public AbstractService() {
    //
  }
}
