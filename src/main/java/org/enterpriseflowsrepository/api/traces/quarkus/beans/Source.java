
package org.enterpriseflowsrepository.api.traces.quarkus.beans;

import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonValue;


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

    /**
     * Name of protocol.
     * 
     */
    @JsonProperty("protocol")
    @JsonPropertyDescription("Name of protocol.")
    private Source.Protocol protocol;
    /**
     * Name of source. Text free.
     * 
     */
    @JsonProperty("source")
    @JsonPropertyDescription("Name of source. Text free.")
    private String source;

    /**
     * Name of protocol.
     * 
     */
    @JsonProperty("protocol")
    public Source.Protocol getProtocol() {
        return protocol;
    }

    /**
     * Name of protocol.
     * 
     */
    @JsonProperty("protocol")
    public void setProtocol(Source.Protocol protocol) {
        this.protocol = protocol;
    }

    /**
     * Name of source. Text free.
     * 
     */
    @JsonProperty("source")
    public String getSource() {
        return source;
    }

    /**
     * Name of source. Text free.
     * 
     */
    @JsonProperty("source")
    public void setSource(String source) {
        this.source = source;
    }

    public enum Protocol {

        file("file"),
        queue("queue"),
        http("http"),
        stream("stream");
        private final String value;
        private final static Map<String, Source.Protocol> CONSTANTS = new HashMap<String, Source.Protocol>();

        static {
            for (Source.Protocol c: values()) {
                CONSTANTS.put(c.value, c);
            }
        }

        private Protocol(String value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return this.value;
        }

        @JsonValue
        public String value() {
            return this.value;
        }

        @JsonCreator
        public static Source.Protocol fromValue(String value) {
            Source.Protocol constant = CONSTANTS.get(value);
            if (constant == null) {
                throw new IllegalArgumentException(value);
            } else {
                return constant;
            }
        }

    }

}
