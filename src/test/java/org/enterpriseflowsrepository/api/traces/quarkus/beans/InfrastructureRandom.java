package org.enterpriseflowsrepository.api.traces.quarkus.beans;

import static io.qala.datagen.RandomShortApi.alphanumeric;

public class InfrastructureRandom extends Infrastructure {

    public InfrastructureRandom() {
        super(alphanumeric(0, 1000));
    }
}
