
package org.enterpriseflowsrepository.api.traces.quarkus.model;

import java.util.ArrayList;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;

import org.enterpriseflowsrepository.api.traces.quarkus.clients.elasticsearch.AbstractDocument;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class TraceModel extends AbstractDocument {

    /**
     * Name of environement. (Required)
     * 
     */
    @JsonProperty("environnement")
    @JsonPropertyDescription("Name of environement.")
    private String environnement;

    /**
     * Message description. (Required)
     * 
     */
    @JsonProperty("message")
    @JsonPropertyDescription("Message description.")
    private MessageModel message;

    /**
     * Route used by the message. (Required)
     * 
     */
    @JsonProperty("route")
    @JsonPropertyDescription("Route used by the message.")
    private RouteModel route;

    /**
     * Business keys in this message.
     * 
     */
    @JsonProperty("business")
    @JsonPropertyDescription("Business keys in this message.")
    private List<KeyModel> business = new ArrayList<>();

    /**
     * Source for Input or Output.
     * 
     */
    @JsonProperty("origin")
    @JsonPropertyDescription("source for Input or Output.")
    private SourceModel origin;

    /**
     * Exception occurs during transport.
     * 
     */
    @JsonProperty("exception")
    @JsonPropertyDescription("Exception occurs during transport.")
    private ExceptionModel exception;

    /**
     * Infrastructure where transport is executed. (Required)
     * 
     */
    @JsonProperty("infrastructure")
    @JsonPropertyDescription("Infrastructure where transport is executed.")
    private InfrastructureModel infrastructure;

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
     * Message description. (Required)
     * 
     */
    @JsonProperty("message")
    public MessageModel getMessage() {
        return message;
    }

    /**
     * Message description. (Required)
     * 
     */
    @JsonProperty("message")
    public void setMessage(MessageModel message) {
        this.message = message;
    }

    /**
     * Route used by the message. (Required)
     * 
     */
    @JsonProperty("route")
    public RouteModel getRoute() {
        return route;
    }

    /**
     * Route used by the message. (Required)
     * 
     */
    @JsonProperty("route")
    public void setRoute(RouteModel route) {
        this.route = route;
    }

    /**
     * Business keys in this message.
     * 
     */
    @JsonProperty("business")
    public List<KeyModel> getBusiness() {
        return business;
    }

    /**
     * Business keys in this message.
     * 
     */
    @JsonProperty("business")
    public void setBusiness(List<KeyModel> business) {
        this.business = business;
    }

    /**
     * Source for Input or Output.
     * 
     */
    @JsonProperty("origin")
    public SourceModel getOrigin() {
        return origin;
    }

    /**
     * Source for Input or Output.
     * 
     */
    @JsonProperty("origin")
    public void setOrigin(SourceModel origin) {
        this.origin = origin;
    }

    /**
     * Exception occurs during transport.
     * 
     */
    @JsonProperty("exception")
    public ExceptionModel getException() {
        return exception;
    }

    /**
     * Exception occurs during transport.
     * 
     */
    @JsonProperty("exception")
    public void setException(ExceptionModel exception) {
        this.exception = exception;
    }

    /**
     * Infrastructure where transport is executed. (Required)
     * 
     */
    @JsonProperty("infrastructure")
    public InfrastructureModel getInfrastructure() {
        return infrastructure;
    }

    /**
     * Infrastructure where transport is executed. (Required)
     * 
     */
    @JsonProperty("infrastructure")
    public void setInfrastructure(InfrastructureModel infrastructure) {
        this.infrastructure = infrastructure;
    }
}
