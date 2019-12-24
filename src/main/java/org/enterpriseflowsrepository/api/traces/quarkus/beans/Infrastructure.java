
package org.enterpriseflowsrepository.api.traces.quarkus.beans;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

/**
 * Root Type for Infrastructure
 * <p>
 * Infrastructure where transport is executed.
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "hostname" })
public class Infrastructure {

    /**
     * host of server.
     * 
     */
    @JsonProperty("hostname")
    @JsonPropertyDescription("host of server.")
    private String hostname;

    /**
     * host of server.
     * 
     */
    @JsonProperty("hostname")
    public String getHostname() {
        return hostname;
    }

    /**
     * host of server.
     * 
     */
    @JsonProperty("hostname")
    public void setHostname(String hostname) {
        this.hostname = hostname;
    }

}
