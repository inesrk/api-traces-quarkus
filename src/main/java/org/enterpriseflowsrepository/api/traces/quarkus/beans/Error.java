
package org.enterpriseflowsrepository.api.traces.quarkus.beans;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

/**
 * Root Type for Error
 * <p>
 * Generic error.
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "code", "description", "details", "origin" })
public class Error {

    /**
     * Code of this error: AAAA-0000
     * 
     */
    @JsonProperty("code")
    @JsonPropertyDescription("Code of this error: AAAA-0000")
    private String code;
    /**
     * Textual description.
     * 
     */
    @JsonProperty("description")
    @JsonPropertyDescription("Textual description.")
    private String description;
    /**
     * Complete stacktrace or long text to technical details.
     * 
     */
    @JsonProperty("details")
    @JsonPropertyDescription("Complete stacktrace or long text to technical details.")
    private String details;
    /**
     * EL path of this error.
     * 
     */
    @JsonProperty("origin")
    @JsonPropertyDescription("EL path of this error.")
    private String origin;

    /**
     * Code of this error: AAAA-0000
     * 
     */
    @JsonProperty("code")
    public String getCode() {
        return code;
    }

    /**
     * Code of this error: AAAA-0000
     * 
     */
    @JsonProperty("code")
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * Textual description.
     * 
     */
    @JsonProperty("description")
    public String getDescription() {
        return description;
    }

    /**
     * Textual description.
     * 
     */
    @JsonProperty("description")
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Complete stacktrace or long text to technical details.
     * 
     */
    @JsonProperty("details")
    public String getDetails() {
        return details;
    }

    /**
     * Complete stacktrace or long text to technical details.
     * 
     */
    @JsonProperty("details")
    public void setDetails(String details) {
        this.details = details;
    }

    /**
     * EL path of this error.
     * 
     */
    @JsonProperty("origin")
    public String getOrigin() {
        return origin;
    }

    /**
     * EL path of this error.
     * 
     */
    @JsonProperty("origin")
    public void setOrigin(String origin) {
        this.origin = origin;
    }

}
