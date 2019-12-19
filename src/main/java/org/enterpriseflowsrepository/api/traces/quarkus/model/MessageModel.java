
package org.enterpriseflowsrepository.api.traces.quarkus.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;
import com.fasterxml.jackson.annotation.JsonValue;


@JsonInclude(JsonInclude.Include.NON_NULL)
public class MessageModel {

    /**
     * Date of message creation.
     * 
     */
    @JsonProperty("created")
    @JsonPropertyDescription("Date of message creation.")
    private Date created;

    /**
     * ID share during some message.
     * ID of a travel between Application Source to Application Target.
     * 
     */
    @JsonProperty("CorrelationID")
    @JsonPropertyDescription("ID share during some message.\nID of a travel between Application Source to Application Target.")
    private String correlationID;
    
    /**
     * ID unique of this message.
     * (Required)
     * 
     */
    @JsonProperty("MessageID")
    @JsonPropertyDescription("ID unique of this message.")
    private String messageID;
    
    /**
     * Headers of this message.
     * 
     */
    @JsonProperty("headers")
    @JsonPropertyDescription("Headers of this message.")
    private List<KeyModel> headers = new ArrayList<KeyModel>();
    
    /**
     * Type of this trace.
     * 
     */
    @JsonProperty("type")
    @JsonPropertyDescription("Type of this trace.")
    private MessageModel.Type type;
    
    /**
     * Level of this trace.
     * (Required)
     * 
     */
    @JsonProperty("level")
    @JsonPropertyDescription("Level of this trace.")
    private MessageModel.Level level;
    
    /**
     * Body of message.
     * 
     */
    @JsonProperty("body")
    @JsonPropertyDescription("Body of message.")
    private String body;

    /**
     * Date of message creation.
     * 
     */
    @JsonProperty("created")
    public Date getCreated() {
        return created;
    }

    /**
     * Date of message creation.
     * 
     */
    @JsonProperty("created")
    public void setCreated(Date created) {
        this.created = created;
    }

    /**
     * ID share during some message.
     * ID of a travel between Application Source to Application Target.
     * 
     */
    @JsonProperty("CorrelationID")
    public String getCorrelationID() {
        return correlationID;
    }

    /**
     * ID share during some message.
     * ID of a travel between Application Source to Application Target.
     * 
     */
    @JsonProperty("CorrelationID")
    public void setCorrelationID(String correlationID) {
        this.correlationID = correlationID;
    }

    /**
     * ID unique of this message.
     * (Required)
     * 
     */
    @JsonProperty("MessageID")
    public String getMessageID() {
        return messageID;
    }

    /**
     * ID unique of this message.
     * (Required)
     * 
     */
    @JsonProperty("MessageID")
    public void setMessageID(String messageID) {
        this.messageID = messageID;
    }

    /**
     * Headers of this message.
     * 
     */
    @JsonProperty("headers")
    public List<KeyModel> getHeaders() {
        return headers;
    }

    /**
     * Headers of this message.
     * 
     */
    @JsonProperty("headers")
    public void setHeaders(List<KeyModel> headers) {
        this.headers = headers;
    }

    /**
     * Type of this trace.
     * 
     */
    @JsonProperty("type")
    public MessageModel.Type getType() {
        return type;
    }

    /**
     * Type of this trace.
     * 
     */
    @JsonProperty("type")
    public void setType(MessageModel.Type type) {
        this.type = type;
    }

    /**
     * Level of this trace.
     * (Required)
     * 
     */
    @JsonProperty("level")
    public MessageModel.Level getLevel() {
        return level;
    }

    /**
     * Level of this trace.
     * (Required)
     * 
     */
    @JsonProperty("level")
    public void setLevel(MessageModel.Level level) {
        this.level = level;
    }

    /**
     * Body of message.
     * 
     */
    @JsonProperty("body")
    public String getBody() {
        return body;
    }

    /**
     * Body of message.
     * 
     */
    @JsonProperty("body")
    public void setBody(String body) {
        this.body = body;
    }

    public enum Level {
        fatal("fatal"),
        error("error"),
        warn("warn"),
        info("info");

        private final String value;
        private final static Map<String, MessageModel.Level> CONSTANTS = new HashMap<String, MessageModel.Level>();

        static {
            for (MessageModel.Level c: values()) {
                CONSTANTS.put(c.value, c);
            }
        }

        private Level(String value) {
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
        public static MessageModel.Level fromValue(String value) {
            MessageModel.Level constant = CONSTANTS.get(value);
            if (constant == null) {
                throw new IllegalArgumentException(value);
            } else {
                return constant;
            }
        }
    }

    public enum Type {
        error("error"),
        business("business"),
        technical("technical");

        private final String value;
        private final static Map<String, MessageModel.Type> CONSTANTS = new HashMap<String, MessageModel.Type>();

        static {
            for (MessageModel.Type c: values()) {
                CONSTANTS.put(c.value, c);
            }
        }

        private Type(String value) {
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
        public static MessageModel.Type fromValue(String value) {
            MessageModel.Type constant = CONSTANTS.get(value);
            if (constant == null) {
                throw new IllegalArgumentException(value);
            } else {
                return constant;
            }
        }
    }
}
