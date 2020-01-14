package org.enterpriseflowsrepository.api.traces.quarkus.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.BadRequestException;

import org.enterpriseflowsrepository.api.traces.quarkus.utils.DateParameter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import io.quarkus.test.junit.QuarkusTest;

@DisplayName("Service 'Trace' - GET operation")
@QuarkusTest
public class TraceServiceGetTest {

  @Inject
  TraceService traceService;

  @Test
  void testStandardReq() {
    //  Thursday, January 1, 2020 9:41:00 AM
    DateParameter after = new DateParameter(new Date(1577871660000L));
    //  Thursday, January 2, 2020 9:41:00 AM
    DateParameter before = new DateParameter(new Date(1577958060000L));
    List<String> keys = new ArrayList<>();
    keys.add("dummy");

    Assertions.assertEquals(new ArrayList<>(), traceService.getTraces(after, before, keys));
  }

  @Test
  void testSameDatesReq() {
    DateParameter after = new DateParameter(new Date());
    DateParameter before = new DateParameter(new Date());
    List<String> keys = new ArrayList<>();
    keys.add("dummy");

    Assertions.assertEquals(new ArrayList<>(), traceService.getTraces(after, before, keys));
  }

  @Test
  void testAfterGreaterThanBeforeReq() {
    //  Thursday, January 2, 2020 9:41:00 AM
    DateParameter after = new DateParameter(new Date(1577958060000L));
    //  Thursday, January 1, 2020 9:41:00 AM
    DateParameter before = new DateParameter(new Date(1577871660000L));
    List<String> keys = new ArrayList<>();
    keys.add("dummy");

    Assertions.assertThrows(BadRequestException.class, () -> traceService.getTraces(after, before, keys));
  }

  @Test
  void testNullAfterDateReq() {
    DateParameter before = new DateParameter(new Date());
    List<String> keys = new ArrayList<>();
    keys.add("dummy");

    Assertions.assertThrows(BadRequestException.class, () -> traceService.getTraces(null, before, keys));
  }

  @Test
  void testNullBeforeDateReq() {
    DateParameter after = new DateParameter(new Date());
    List<String> keys = new ArrayList<>();
    keys.add("dummy");

    Assertions.assertThrows(BadRequestException.class, () -> traceService.getTraces(after, null, keys));
  }

  @Test
  void testEmptyKeysReq() {
    DateParameter after = new DateParameter(new Date());
    DateParameter before = new DateParameter(new Date());
    List<String> keys = new ArrayList<>();

    Assertions.assertEquals(new ArrayList<>(), traceService.getTraces(after, before, keys));
  }

  @Test
  void testNullKeysReq() {
    DateParameter after = new DateParameter(new Date());
    DateParameter before = new DateParameter(new Date());

    Assertions.assertEquals(new ArrayList<>(), traceService.getTraces(after, before, null));
  }
}
