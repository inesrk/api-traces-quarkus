package org.enterpriseflowsrepository.api.traces.quarkus;

import java.lang.String;
import java.util.Date;
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
import org.enterpriseflowsrepository.api.traces.quarkus.beans.Trace;

/**
 * A JAX-RS interface.  An implementation of this interface must be provided.
 */
@Path("/trace")
public interface TraceResource {
  /**
   * Gets a list of all `Trace` entities.
   */
  @GET
  @Produces("application/json")
  List<Trace> gettraces(@QueryParam("after") Date after, @QueryParam("before") Date before,
      @QueryParam("keys") List<String> keys);

  /**
   * Creates a new instance of a `Trace`.
   */
  @POST
  @Consumes("application/json")
  void createTrace(Trace data);

  /**
   * Gets the details of a single instance of a `Trace`.
   */
  @Path("/{traceId}")
  @GET
  @Produces("application/json")
  Trace getTrace(@PathParam("traceId") String traceId);

  /**
   * Updates an existing `Trace`.
   */
  @Path("/{traceId}")
  @PUT
  @Consumes("application/json")
  void updateTrace(@PathParam("traceId") String traceId, Trace data);

  /**
   * Deletes an existing `Trace`.
   */
  @Path("/{traceId}")
  @DELETE
  void deleteTrace(@PathParam("traceId") String traceId);
}
