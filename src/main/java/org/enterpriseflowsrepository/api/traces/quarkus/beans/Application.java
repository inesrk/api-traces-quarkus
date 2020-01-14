
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
@JsonPropertyOrder({ "informationSystem", "application", "hostname", "created" })
public class Application {

    @JsonProperty("informationSystem")
    private String informationSystem;
    
    /**
     * Name of application.
     */
    @JsonProperty("application")
    @JsonPropertyDescription("Name of application.")
    private String application;
    
    @JsonProperty("hostname")
    private String hostname;
    
    /**
     * Creation date of send.
     */
    @JsonProperty("created")
    @JsonPropertyDescription("Creation date of send.")
    private Date created;

    /**
     * Standard constructor.
     */
    public Application() {
        // nothing here
    }
    
    /**
     * Internal constructor, to be used in tests.
     * 
     * @param informationSystem
     * @param application
     * @param hostname
     * @param created
     */
    Application(String informationSystem, String application, String hostname, Date created) {
        this.informationSystem = informationSystem;
        this.application = application;
        this.hostname = hostname;
        this.created = created;
    }

    @JsonProperty("informationSystem")
    public String getInformationSystem() {
        return informationSystem;
    }

    @JsonProperty("informationSystem")
    public void setInformationSystem(String informationSystem) {
        this.informationSystem = informationSystem;
    }

    /**
     * Name of application.
     */
    @JsonProperty("application")
    public String getApplication() {
        return application;
    }

    /**
     * Name of application.
     */
    @JsonProperty("application")
    public void setApplication(String application) {
        this.application = application;
    }

    @JsonProperty("hostname")
    public String getHostname() {
        return hostname;
    }

    @JsonProperty("hostname")
    public void setHostname(String hostname) {
        this.hostname = hostname;
    }

    /**
     * Creation date of send.
     */
    @JsonProperty("created")
    public Date getCreated() {
        return created;
    }

    /**
     * Creation date of send.
     */
    @JsonProperty("created")
    public void setCreated(Date created) {
        this.created = created;
    }

}
