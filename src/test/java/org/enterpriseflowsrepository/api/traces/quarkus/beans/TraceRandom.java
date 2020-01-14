package org.enterpriseflowsrepository.api.traces.quarkus.beans;

import static io.qala.datagen.RandomShortApi.Long;
import static io.qala.datagen.RandomShortApi.alphanumeric;
import static io.qala.datagen.RandomShortApi.unicode;
import static io.qala.datagen.RandomValue.between;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TraceRandom extends Trace {

    public TraceRandom() {
        super(
            alphanumeric(1, 24),
            Long(),
            unicode(0, 1000),
            (Message) new MessageRandom(),
            (Route) new RouteRandom(),
            (List<Key>) new ArrayList<Key>() {
                    private static final long serialVersionUID = -349857538773856L;

                    {
                        add(new KeyRandom());
                    }
                },
            (Source) new SourceRandom(),
            (Exception) new ExceptionRandom(),
            (Infrastructure) new InfrastructureRandom(),
            between(new Date(1577871660000L), new Date(1577958060000L)).date());
    }
}
