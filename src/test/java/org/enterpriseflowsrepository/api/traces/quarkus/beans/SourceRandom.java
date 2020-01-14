package org.enterpriseflowsrepository.api.traces.quarkus.beans;

import static io.qala.datagen.RandomShortApi.unicode;

public class SourceRandom extends Source {

    public SourceRandom() {
        super(
            unicode(0, 1000),
            unicode(0, 1000));
    }
}
