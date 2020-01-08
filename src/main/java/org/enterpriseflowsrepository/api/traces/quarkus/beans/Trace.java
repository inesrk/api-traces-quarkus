
package org.enterpriseflowsrepository.api.traces.quarkus.beans;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;

import org.eclipse.microprofile.openapi.annotations.media.Schema;

/**
 * Root Type for Trace
 * <p>
 * Trace of a message.
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "id", "version", "environnement", "message", "route", "business", "origin", "exception", "infrastructure", "created" })
@Schema(name="Trace", description="POJO that represents a trace.")
public class Trace {

    /**
     * Identifier of this object.
     * 
     */
    @JsonProperty("id")
    @JsonPropertyDescription("Identifier of this object.")
    private String id;

    /**
     * Version of this format. (Required)
     * 
     */
    @JsonProperty("version")
    @JsonPropertyDescription("Version of this format.")
    private Long version;

    /**
     * Name of environement. (Required)
     * 
     */
    @JsonProperty("environnement")
    @JsonPropertyDescription("Name of environement.")
    private String environnement;
    
    /**
     * Root Type for Message
     * <p>
     * Message description. (Required)
     * 
     */
    @JsonProperty("message")
    @JsonPropertyDescription("Message description.")
    private Message message;
    
    /**
     * Root Type for Route
     * <p>
     * Route used by the message. (Required)
     * 
     */
    @JsonProperty("route")
    @JsonPropertyDescription("Route used by the message.")
    private Route route;
    
    /**
     * Business keys in this message.
     * 
     */
    @JsonProperty("business")
    @JsonPropertyDescription("Business keys in this message.")
    private List<Key> business = new ArrayList<>();
    
    /**
     * Root Type for Source
     * <p>
     * source for Input or Output.
     * 
     */
    @JsonProperty("origin")
    @JsonPropertyDescription("source for Input or Output.")
    private Source origin;
    
    /**
     * Root Type for Exception
     * <p>
     * Exception occurs during transport.
     * 
     */
    @JsonProperty("exception")
    @JsonPropertyDescription("Exception occurs during transport.")
    private Exception exception;
    
    /**
     * Root Type for Infrastructure
     * <p>
     * Infrastructure where transport is executed. (Required)
     * 
     */
    @JsonProperty("infrastructure")
    @JsonPropertyDescription("Infrastructure where transport is executed.")
    private Infrastructure infrastructure;

    /**
     * Date of document creation.
     * 
     */
    @JsonProperty("created")
    @JsonPropertyDescription("Date of document creation.")
    @JsonFormat(shape = Shape.STRING)
    private Date created = new Date();

    /**
     * Identifier of this object.
     * 
     */
    @JsonProperty("id")
    public String getId() {
        return id;
    }

    /**
     * Identifier of this object.
     * 
     */
    @JsonProperty("id")
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Version of this format. (Required)
     * 
     */
    @JsonProperty("version")
    public Long getVersion() {
        return version;
    }

    /**
     * Version of this format. (Required)
     * 
     */
    @JsonProperty("version")
    public void setVersion(Long version) {
        this.version = version;
    }

    /**
     * Name of environement. (Required)
     * 
     */
    @JsonProperty("environnement")
    public String getEnvironnement() {
        return environnement;
    }

    /**
     * Name of environement. (Required)
     * 
     */
    @JsonProperty("environnement")
    public void setEnvironnement(String environnement) {
        this.environnement = environnement;
    }

    /**
     * Root Type for Message
     * <p>
     * Message description. (Required)
     * 
     */
    @JsonProperty("message")
    public Message getMessage() {
        return message;
    }

    /**
     * Root Type for Message
     * <p>
     * Message description. (Required)
     * 
     */
    @JsonProperty("message")
    public void setMessage(Message message) {
        this.message = message;
    }

    /**
     * Root Type for Route
     * <p>
     * Route used by the message. (Required)
     * 
     */
    @JsonProperty("route")
    public Route getRoute() {
        return route;
    }

    /**
     * Root Type for Route
     * <p>
     * Route used by the message. (Required)
     * 
     */
    @JsonProperty("route")
    public void setRoute(Route route) {
        this.route = route;
    }

    /**
     * Business keys in this message.
     * 
     */
    @JsonProperty("business")
    public List<Key> getBusiness() {
        return business;
    }

    /**
     * Business keys in this message.
     * 
     */
    @JsonProperty("business")
    public void setBusiness(List<Key> business) {
        this.business = business;
    }

    /**
     * Root Type for Source
     * <p>
     * source for Input or Output.
     * 
     */
    @JsonProperty("origin")
    public Source getOrigin() {
        return origin;
    }

    /**
     * Root Type for Source
     * <p>
     * source for Input or Output.
     * 
     */
    @JsonProperty("origin")
    public void setOrigin(Source origin) {
        this.origin = origin;
    }

    /**
     * Root Type for Exception
     * <p>
     * Exception occurs during transport.
     * 
     */
    @JsonProperty("exception")
    public Exception getException() {
        return exception;
    }

    /**
     * Root Type for Exception
     * <p>
     * Exception occurs during transport.
     * 
     */
    @JsonProperty("exception")
    public void setException(Exception exception) {
        this.exception = exception;
    }

    /**
     * Root Type for Infrastructure
     * <p>
     * Infrastructure where transport is executed. (Required)
     * 
     */
    @JsonProperty("infrastructure")
    public Infrastructure getInfrastructure() {
        return infrastructure;
    }

    /**
     * Root Type for Infrastructure
     * <p>
     * Infrastructure where transport is executed. (Required)
     * 
     */
    @JsonProperty("infrastructure")
    public void setInfrastructure(Infrastructure infrastructure) {
        this.infrastructure = infrastructure;
    }

    /**
     * Date of document creation.
     * 
     */
    @JsonProperty("created")
    public Date getCreated() {
        return created;
    }

    /**
     * Date of document creation.
     * 
     */
    @JsonProperty("created")
    public void setCreated(Date created) {
        this.created = created;
    }
}
