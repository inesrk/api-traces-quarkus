package org.enterpriseflowsrepository.api.traces.quarkus;

import java.lang.String;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.enterpriseflowsrepository.api.traces.quarkus.beans.Trace;
import org.enterpriseflowsrepository.api.traces.quarkus.utils.DateParameter;

/**
 * A JAX-RS interface. An implementation of this interface must be provided.
 */
@Path("/trace")
public interface TraceResource {

  /**
   * Gets a list of all 'Trace' entities.
   */
  @GET
  @Produces(MediaType.APPLICATION_JSON)
  List<Trace> getTraces(@QueryParam("after") DateParameter after, @QueryParam("before") DateParameter before,
      @QueryParam("keys") List<String> keys);

  /**
   * Bulk usage for put some traces.
   */
  @PUT
  @Consumes(MediaType.APPLICATION_JSON)
  Response bulkTraces(List<Trace> data);

  /**
   * Creates a new instance of a 'Trace'.
   */
  @POST
  @Consumes(MediaType.APPLICATION_JSON)
  Response createTrace(Trace data);
}
