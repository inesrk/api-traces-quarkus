package org.enterpriseflowsrepository.api.traces.quarkus.beans;

import static io.qala.datagen.RandomShortApi.unicode;

public class KeyRandom extends Key {

    public KeyRandom() {
        super(
            unicode(0, 1000),
            unicode(0, 1000));
    }
}
