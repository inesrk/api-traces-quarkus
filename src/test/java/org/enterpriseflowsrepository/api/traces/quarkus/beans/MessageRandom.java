package org.enterpriseflowsrepository.api.traces.quarkus.beans;

import static io.qala.datagen.RandomShortApi.alphanumeric;
import static io.qala.datagen.RandomShortApi.sample;
import static io.qala.datagen.RandomShortApi.unicode;
import static io.qala.datagen.RandomValue.between;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MessageRandom extends Message {

    public MessageRandom() {
        super(
            between(new Date(1577871660000L), new Date(1577958060000L)).date(),
            alphanumeric(0, 1000),
            alphanumeric(0, 1000),
            (List<Key>) new ArrayList<Key>() {
                    private static final long serialVersionUID = -349857538773856L;

                    {
                        add(new KeyRandom());
                    }
                },
            sample(Message.Type.values()),
            sample(Message.Level.values()),
            unicode(0, 1000));
    }
}
