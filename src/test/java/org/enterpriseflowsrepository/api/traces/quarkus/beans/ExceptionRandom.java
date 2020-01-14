package org.enterpriseflowsrepository.api.traces.quarkus.beans;

import static io.qala.datagen.RandomShortApi.unicode;

public class ExceptionRandom extends Exception {

    public ExceptionRandom() {
        super(
            unicode(0, 1000),
            unicode(0, 1000),
            unicode(0, 1000),
            unicode(0, 1000));
    }
}
