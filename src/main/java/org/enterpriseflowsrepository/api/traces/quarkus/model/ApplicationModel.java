
package org.enterpriseflowsrepository.api.traces.quarkus.model;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;


@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApplicationModel {

    @JsonProperty("informationSystem")
    private String informationSystem;
    
    /**
     * Name of application.
     * 
     */
    @JsonProperty("application")
    @JsonPropertyDescription("Name of application.")
    private String application;
    
    @JsonProperty("hostname")
    private String hostname;
    
    /**
     * Creation date of send.
     * 
     */
    @JsonProperty("created")
    @JsonPropertyDescription("Creation date of send.")
    private Date created;

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
