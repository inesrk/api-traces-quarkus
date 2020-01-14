
package org.enterpriseflowsrepository.api.traces.quarkus.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class InfrastructureModel {

    /**
     * host of server.
     */
    @JsonProperty("hostname")
    @JsonPropertyDescription("host of server.")
    private String hostname;

    /**
     * host of server.
     */
    @JsonProperty("hostname")
    public String getHostname() {
        return hostname;
    }

    /**
     * host of server.
     */
    @JsonProperty("hostname")
    public void setHostname(String hostname) {
        this.hostname = hostname;
    }
}
