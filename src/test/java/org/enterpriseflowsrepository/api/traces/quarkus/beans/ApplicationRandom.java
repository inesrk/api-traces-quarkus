package org.enterpriseflowsrepository.api.traces.quarkus.beans;

import static io.qala.datagen.RandomShortApi.alphanumeric;
import static io.qala.datagen.RandomShortApi.unicode;
import static io.qala.datagen.RandomValue.between;

import java.util.Date;

public class ApplicationRandom extends Application {

    public ApplicationRandom() {
        super(
            unicode(0, 1000),
            unicode(0, 1000),
            alphanumeric(0, 1000),
            between(new Date(1577871660000L), new Date(1577958060000L)).date());
    }
}
