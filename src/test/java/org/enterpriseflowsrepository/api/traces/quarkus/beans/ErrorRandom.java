package org.enterpriseflowsrepository.api.traces.quarkus.beans;

import static io.qala.datagen.RandomShortApi.unicode;

public class ErrorRandom extends Error {

    public ErrorRandom() {
        super(
            unicode(0, 1000),
            unicode(0, 1000),
            unicode(0, 1000),
            unicode(0, 1000)
        );
    }
}
