
package org.enterpriseflowsrepository.api.traces.quarkus.beans;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;


/**
 * Root Type for Application
 * <p>
 * Origin or Target application of this message.
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "informationSystem",
    "application",
    "hostname",
    "created"
})
public class Application {

    /**
     * Name of Information System.
     * 
     */
    @JsonProperty("informationSystem")
    @JsonPropertyDescription("Name of Information System.")
    private String informationSystem;
    /**
     * Name of application.
     * 
     */
    @JsonProperty("application")
    @JsonPropertyDescription("Name of application.")
    private String application;
    /**
     * hostname where application is running.
     * 
     */
    @JsonProperty("hostname")
    @JsonPropertyDescription("hostname where application is running.")
    private String hostname;
    /**
     * Creation date of send.
     * 
     */
    @JsonProperty("created")
    @JsonPropertyDescription("Creation date of send.")
    private Date created;

    /**
     * Name of Information System.
     * 
     */
    @JsonProperty("informationSystem")
    public String getInformationSystem() {
        return informationSystem;
    }

    /**
     * Name of Information System.
     * 
     */
    @JsonProperty("informationSystem")
    public void setInformationSystem(String informationSystem) {
        this.informationSystem = informationSystem;
    }

    /**
     * Name of application.
     * 
     */
    @JsonProperty("application")
    public String getApplication() {
        return application;
    }

    /**
     * Name of application.
     * 
     */
    @JsonProperty("application")
    public void setApplication(String application) {
        this.application = application;
    }

    /**
     * hostname where application is running.
     * 
     */
    @JsonProperty("hostname")
    public String getHostname() {
        return hostname;
    }

    /**
     * hostname where application is running.
     * 
     */
    @JsonProperty("hostname")
    public void setHostname(String hostname) {
        this.hostname = hostname;
    }

    /**
     * Creation date of send.
     * 
     */
    @JsonProperty("created")
    public Date getCreated() {
        return created;
    }

    /**
     * Creation date of send.
     * 
     */
    @JsonProperty("created")
    public void setCreated(Date created) {
        this.created = created;
    }

}
