package org.enterpriseflowsrepository.api.traces.quarkus;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.enums.SchemaType;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.parameters.Parameter;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;
import org.enterpriseflowsrepository.api.traces.quarkus.beans.Trace;
import org.enterpriseflowsrepository.api.traces.quarkus.utils.DateParameter;


/**
 * A JAX-RS interface. An implementation of this interface must be provided.
 */
@Path("/trace")
@Tag(name = "trace", description = "Trace of a message transport.")
public interface TraceResource {

  /**
   * Gets a list of all 'Trace' entities.
   */
  @GET
  @Produces(MediaType.APPLICATION_JSON)
  @Operation(summary = "Find Trace objects by date (inc./excl.) plus keys", description = "Use this verb to perform search operations. Asynchronous call.")
  @APIResponses(value = {
    @APIResponse(responseCode = "200", description = "List of Trace objects.", content = @Content(schema = @Schema(type = SchemaType.ARRAY, implementation = Trace.class))),
    @APIResponse(responseCode = "400", description = "Request is malformulated.")})
  List<Trace> getTraces(
    @QueryParam("after") @Parameter(description = "All returned objects will have been created after or at that date.", schema = @Schema(type = SchemaType.STRING, description = "JSON valid datetime representation", example = "2010-09-30T12:30:04.123"), required = true) DateParameter after, 
    @QueryParam("before") @Parameter(description = "All returned objects will have been created before or at that date.", schema = @Schema(type = SchemaType.STRING, description = "JSON valid datetime representation", example = "2010-09-30T12:30:04.123"), required = true) DateParameter before,
    @QueryParam("keys") @Parameter(description = "All returned objects will have all the corresponding keys. Please list them with comas.") List<String> keys);

  /**
   * Bulk usage for put some traces.
   */
  @PUT
  @Consumes(MediaType.APPLICATION_JSON)
  @Operation(summary = "Bulk insert multiple Trace objects", description = "Use this verb when you already have multiple objects to imports, this wil have a much lower footprint on the system. Asynchronous call.")
  @APIResponses(value = {
    @APIResponse(responseCode = "201", description = "Objects are stored."),
    @APIResponse(responseCode = "400", description = "Request body is null or empty.")})
  @RequestBody(description = "Collection of Trace objects", required = true)
  Response bulkTraces(List<Trace> data);

  /**
   * Creates a new instance of a 'Trace'.
   */
  @POST
  @Consumes(MediaType.APPLICATION_JSON)
  @Operation(summary = "Insert a single Trace object", description = "Use this verb to import a single object. Asynchronous call.")
  @APIResponses(value = {
    @APIResponse(responseCode = "201", description = "Objects are stored."),
    @APIResponse(responseCode = "400", description = "Request body is null or empty.")})
  @RequestBody(description = "Trace object", required = true)
  Response createTrace(Trace data);
}
