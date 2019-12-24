package org.enterpriseflowsrepository.api.traces.quarkus.clients.elasticsearch;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;

public class AbstractDocument {

    /**
     * Unique identifier of the document.
     */
    private String id;

    /**
     * Version of the document.
     * 
     */
    private Long version;

    /**
     * Date of document creation.
     * 
     */
    @JsonProperty("created")
    @JsonPropertyDescription("Date of document creation.")
    @JsonFormat(shape = Shape.STRING)
    private Date created;

    /**
     * Date of document last update.
     * 
     */
    @JsonProperty("updated")
    @JsonPropertyDescription("Date of document last update.")
    @JsonFormat(shape = Shape.STRING)
    private Date updated;

    /**
     * Unique identifier of the document.
     * 
     */
    public String getId() {
        return id;
    }

    /**
     * Version of the document.
     * 
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Version of the document.
     * 
     */
    public Long getVersion() {
        return version;
    }

    /**
     * Version of the document.
     * 
     */
    public void setVersion(Long version) {
        this.version = version;
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

    /**
     * Date of document last update.
     * 
     */
    @JsonProperty("updated")
    public Date getUpdated() {
        return updated;
    }

    /**
     * Date of document last update.
     * 
     */
    @JsonProperty("updated")
    public void setUpdated(Date updated) {
        this.updated = updated;
    }
}
