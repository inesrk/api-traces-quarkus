package org.enterpriseflowsrepository.api.traces.quarkus.services;

import javax.inject.Inject;
import javax.ws.rs.BadRequestException;
import javax.ws.rs.core.Response.Status;

import org.enterpriseflowsrepository.api.traces.quarkus.beans.Trace;
import org.enterpriseflowsrepository.api.traces.quarkus.beans.TraceRandom;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import io.quarkus.test.junit.QuarkusTest;

@DisplayName("Service 'Trace' - POST operation")
@QuarkusTest
public class TraceServicePostTest {

  @Inject
  TraceService traceService;

  void testStandardReq() {
    Assertions.assertEquals(Status.CREATED.getStatusCode(), traceService.createTrace((Trace) new TraceRandom()).getStatus());
  }

  @Test
  void testNullReq() {
    Assertions.assertThrows(BadRequestException.class, () -> traceService.createTrace(null));
  }
}
