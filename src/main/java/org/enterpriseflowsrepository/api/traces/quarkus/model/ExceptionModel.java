
package org.enterpriseflowsrepository.api.traces.quarkus.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;


@JsonInclude(JsonInclude.Include.NON_NULL)
public class ExceptionModel {

    /**
     * Unique code for this exception.
     * 
     */
    @JsonProperty("code")
    @JsonPropertyDescription("Unique code for this exception.")
    private String code;

    /**
     * Class name.
     * 
     */
    @JsonProperty("class")
    @JsonPropertyDescription("Class name.")
    private String _class;
    
    /**
     * A short text details.
     * 
     */
    @JsonProperty("detail")
    @JsonPropertyDescription("A short text details.")
    private String detail;
    
    /**
     * complete stacktrace of this exception.
     * 
     */
    @JsonProperty("stacktrace")
    @JsonPropertyDescription("complete stacktrace of this exception.")
    private String stacktrace;

    /**
     * Unique code for this exception.
     * 
     */
    @JsonProperty("code")
    public String getCode() {
        return code;
    }

    /**
     * Unique code for this exception.
     * 
     */
    @JsonProperty("code")
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * Class name.
     * 
     */
    @JsonProperty("class")
    public String getClass_() {
        return _class;
    }

    /**
     * Class name.
     * 
     */
    @JsonProperty("class")
    public void setClass_(String _class) {
        this._class = _class;
    }

    /**
     * A short text details.
     * 
     */
    @JsonProperty("detail")
    public String getDetail() {
        return detail;
    }

    /**
     * A short text details.
     * 
     */
    @JsonProperty("detail")
    public void setDetail(String detail) {
        this.detail = detail;
    }

    /**
     * complete stacktrace of this exception.
     * 
     */
    @JsonProperty("stacktrace")
    public String getStacktrace() {
        return stacktrace;
    }

    /**
     * complete stacktrace of this exception.
     * 
     */
    @JsonProperty("stacktrace")
    public void setStacktrace(String stacktrace) {
        this.stacktrace = stacktrace;
    }
}
