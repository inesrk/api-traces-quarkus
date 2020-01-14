package org.enterpriseflowsrepository.api.traces.quarkus.services;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.BadRequestException;
import javax.ws.rs.core.Response.Status;

import org.enterpriseflowsrepository.api.traces.quarkus.beans.Trace;
import org.enterpriseflowsrepository.api.traces.quarkus.beans.TraceRandom;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import io.qala.datagen.junit.jupiter.RandomInt;
import io.quarkus.test.junit.QuarkusTest;

@DisplayName("Service 'Trace' - PUT operation")
@QuarkusTest
public class TraceServicePutTest {

  @Inject
  TraceService traceService;

  // TODO: Fix issue "io.qala.datagen.junit.jupiter.RandomIntArgumentProvider must be used with an annotation of type io.qala.datagen.junit.jupiter.RandomInt"
  // @RandomInt(min = 1, max = 10, name = "between 1 and 10")
  @RandomInt(min = 100, max = 1000, name = "between 100 and 1000")
  void testStandardReq(int param, String name) {
    List<Trace> traces = new ArrayList<>(param);

    for (int i = 0; i < param; i++) {
      traces.add(new TraceRandom());
    }

    Assertions.assertEquals(Status.CREATED.getStatusCode(), traceService.bulkTraces(traces).getStatus(), "Failed case: " + name);
  }

  @Test
  void testEmptyReq() {
    Assertions.assertThrows(BadRequestException.class, () -> traceService.bulkTraces(new ArrayList<>()));
  }

  @Test
  void testNullReq() {
    Assertions.assertThrows(BadRequestException.class, () -> traceService.bulkTraces(null));
  }
}
