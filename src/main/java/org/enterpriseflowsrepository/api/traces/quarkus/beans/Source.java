
package org.enterpriseflowsrepository.api.traces.quarkus.beans;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;


/**
 * Root Type for Source
 * <p>
 * source for Input or Output.
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "protocol",
    "source"
})
public class Source {

    @JsonProperty("protocol")
    private String protocol;
    @JsonProperty("source")
    private String source;

    @JsonProperty("protocol")
    public String getProtocol() {
        return protocol;
    }

    @JsonProperty("protocol")
    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }

    @JsonProperty("source")
    public String getSource() {
        return source;
    }

    @JsonProperty("source")
    public void setSource(String source) {
        this.source = source;
    }

}
