package org.enterpriseflowsrepository.api.traces.quarkus.beans;

import static io.qala.datagen.RandomShortApi.alphanumeric;
import static io.qala.datagen.RandomShortApi.unicode;

public class RouteRandom extends Route {

    public RouteRandom() {
        super(
            unicode(0, 1000),
            unicode(0, 1000),
            alphanumeric(0, 1000),
            unicode(0, 1000),
            unicode(0, 1000),
            (Source) new SourceRandom(),
            (Source) new SourceRandom());
    }
}
