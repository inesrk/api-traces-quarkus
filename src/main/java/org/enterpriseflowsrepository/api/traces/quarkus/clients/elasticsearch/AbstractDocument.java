package org.enterpriseflowsrepository.api.traces.quarkus.clients.elasticsearch;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;

public abstract class AbstractDocument {
    
    @JsonProperty("id")
    @JsonPropertyDescription("Identifier of this object.")
    private String id;

    @JsonProperty("id")
    public String getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(String id) {
        this.id = id;
    }
}
