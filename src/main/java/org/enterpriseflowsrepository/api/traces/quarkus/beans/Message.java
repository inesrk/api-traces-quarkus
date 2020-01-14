
package org.enterpriseflowsrepository.api.traces.quarkus.beans;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * Root Type for Message
 * <p>
 * Message description.
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "created", "CorrelationID", "MessageID", "headers", "type", "level", "body" })
public class Message {

    /**
     * Date of message creation.
     */
    @JsonProperty("created")
    @JsonPropertyDescription("Date of message creation.")
    @JsonFormat(shape = Shape.STRING)
    private Date created;
    /**
     * ID share during some message. ID of a travel between Application Source to
     * Application Target.
     */
    @JsonProperty("CorrelationID")
    @JsonPropertyDescription("ID share during some message.\nID of a travel between Application Source to Application Target.")
    private String correlationID;
    /**
     * ID unique of this message. (Required)
     */
    @JsonProperty("MessageID")
    @JsonPropertyDescription("ID unique of this message.")
    private String messageID;
    /**
     * Headers of this message.
     */
    @JsonProperty("headers")
    @JsonPropertyDescription("Headers of this message.")
    private List<Key> headers = new ArrayList<Key>();
    /**
     * Type of this trace.
     */
    @JsonProperty("type")
    @JsonPropertyDescription("Type of this trace.")
    private Message.Type type;
    /**
     * Level of this trace. (Required)
     */
    @JsonProperty("level")
    @JsonPropertyDescription("Level of this trace.")
    private Message.Level level;
    /**
     * Body of message.
     */
    @JsonProperty("body")
    @JsonPropertyDescription("Body of message.")
    private String body;

    /**
     * Standard constructor.
     */
    public Message() {
        // nothing here
    }
    
    /**
     * Internal constructor, to be used in tests.
     * 
     * @param created
     * @param correlationID
     * @param messageID
     * @param headers
     * @param type
     * @param level
     * @param body
     */
    Message(Date created, String correlationID, String messageID, List<Key> headers, Message.Type type, Message.Level level, String body) {
        this.created = created;
        this.correlationID = correlationID;
        this.messageID = messageID;
        this.headers = headers;
        this.type = type;
        this.level = level;
        this.level = level;
        this.body = body;
    }

    /**
     * Date of message creation.
     */
    @JsonProperty("created")
    public Date getCreated() {
        return created;
    }

    /**
     * Date of message creation.
     */
    @JsonProperty("created")
    public void setCreated(Date created) {
        this.created = created;
    }

    /**
     * ID share during some message. ID of a travel between Application Source to
     * Application Target.
     */
    @JsonProperty("CorrelationID")
    public String getCorrelationID() {
        return correlationID;
    }

    /**
     * ID share during some message. ID of a travel between Application Source to
     * Application Target.
     */
    @JsonProperty("CorrelationID")
    public void setCorrelationID(String correlationID) {
        this.correlationID = correlationID;
    }

    /**
     * ID unique of this message. (Required)
     */
    @JsonProperty("MessageID")
    public String getMessageID() {
        return messageID;
    }

    /**
     * ID unique of this message. (Required)
     */
    @JsonProperty("MessageID")
    public void setMessageID(String messageID) {
        this.messageID = messageID;
    }

    /**
     * Headers of this message.
     */
    @JsonProperty("headers")
    public List<Key> getHeaders() {
        return headers;
    }

    /**
     * Headers of this message.
     */
    @JsonProperty("headers")
    public void setHeaders(List<Key> headers) {
        this.headers = headers;
    }

    /**
     * Type of this trace.
     */
    @JsonProperty("type")
    public Message.Type getType() {
        return type;
    }

    /**
     * Type of this trace.
     */
    @JsonProperty("type")
    public void setType(Message.Type type) {
        this.type = type;
    }

    /**
     * Level of this trace. (Required)
     */
    @JsonProperty("level")
    public Message.Level getLevel() {
        return level;
    }

    /**
     * Level of this trace. (Required)
     */
    @JsonProperty("level")
    public void setLevel(Message.Level level) {
        this.level = level;
    }

    /**
     * Body of message.
     */
    @JsonProperty("body")
    public String getBody() {
        return body;
    }

    /**
     * Body of message.
     */
    @JsonProperty("body")
    public void setBody(String body) {
        this.body = body;
    }

    public enum Level {

        fatal("fatal"), error("error"), warn("warn"), info("info");

        private final String value;
        private final static Map<String, Message.Level> CONSTANTS = new HashMap<String, Message.Level>();

        static {
            for (Message.Level c : values()) {
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
        public static Message.Level fromValue(String value) {
            Message.Level constant = CONSTANTS.get(value);
            if (constant == null) {
                throw new IllegalArgumentException(value);
            } else {
                return constant;
            }
        }

    }

    public enum Type {

        error("error"), business("business"), technical("technical");

        private final String value;
        private final static Map<String, Message.Type> CONSTANTS = new HashMap<String, Message.Type>();

        static {
            for (Message.Type c : values()) {
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
        public static Message.Type fromValue(String value) {
            Message.Type constant = CONSTANTS.get(value);
            if (constant == null) {
                throw new IllegalArgumentException(value);
            } else {
                return constant;
            }
        }

    }

}
