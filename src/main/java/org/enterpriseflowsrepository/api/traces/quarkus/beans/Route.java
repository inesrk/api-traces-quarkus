
package org.enterpriseflowsrepository.api.traces.quarkus.beans;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

/**
 * Root Type for Route
 * <p>
 * Route used by the message.
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "name", "version", "id", "step", "description", "input", "output" })
public class Route {

    /**
     * Name of route.
     */
    @JsonProperty("name")
    @JsonPropertyDescription("Name of route.")
    private String name;

    /**
     * Version of route.
     */
    @JsonProperty("version")
    @JsonPropertyDescription("Version of route.")
    private String version;

    /**
     * ID of route.
     */
    @JsonProperty("id")
    @JsonPropertyDescription("ID of route.")
    private String id;
    
    /**
     * Step DURIG thie route.
     */
    @JsonProperty("step")
    @JsonPropertyDescription("Step DURIG thie route.")
    private String step;
    
    /**
     * Textual description of route.
     */
    @JsonProperty("description")
    @JsonPropertyDescription("Textual description of route.")
    private String description;
    
    /**
     * Root Type for Source
     * <p>
     * source for Input or Output.
     */
    @JsonProperty("input")
    @JsonPropertyDescription("source for Input or Output.")
    private Source input;
    
    /**
     * Root Type for Source
     * <p>
     * source for Input or Output.
     */
    @JsonProperty("output")
    @JsonPropertyDescription("source for Input or Output.")
    private Source output;

    /**
     * Standard constructor.
     */
    public Route() {
        // nothing here
    }

    /**
     * Internal constructor, to be used in tests.
     * 
     * @param name
     * @param version
     * @param id
     * @param step
     * @param description
     * @param input
     * @param output
     */
    Route(String name, String version, String id, String step, String description, Source input, Source output) {
        this.name = name;
        this.version = version;
        this.id = id;
        this.step = step;
        this.description = description;
        this.input = input;
        this.output = output;
    }

    /**
     * Name of route.
     */
    @JsonProperty("name")
    public String getName() {
        return name;
    }

    /**
     * Name of route.
     */
    @JsonProperty("name")
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Version of route.
     */
    @JsonProperty("version")
    public String getVersion() {
        return version;
    }

    /**
     * Version of route.
     */
    @JsonProperty("version")
    public void setVersion(String version) {
        this.version = version;
    }

    /**
     * ID of route.
     */
    @JsonProperty("id")
    public String getId() {
        return id;
    }

    /**
     * ID of route.
     */
    @JsonProperty("id")
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Step DURIG thie route.
     */
    @JsonProperty("step")
    public String getStep() {
        return step;
    }

    /**
     * Step DURIG thie route.
     */
    @JsonProperty("step")
    public void setStep(String step) {
        this.step = step;
    }

    /**
     * Textual description of route.
     */
    @JsonProperty("description")
    public String getDescription() {
        return description;
    }

    /**
     * Textual description of route.
     */
    @JsonProperty("description")
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Root Type for Source
     * <p>
     * source for Input or Output.
     */
    @JsonProperty("input")
    public Source getInput() {
        return input;
    }

    /**
     * Root Type for Source
     * <p>
     * source for Input or Output.
     */
    @JsonProperty("input")
    public void setInput(Source input) {
        this.input = input;
    }

    /**
     * Root Type for Source
     * <p>
     * source for Input or Output.
     */
    @JsonProperty("output")
    public Source getOutput() {
        return output;
    }

    /**
     * Root Type for Source
     * <p>
     * source for Input or Output.
     */
    @JsonProperty("output")
    public void setOutput(Source output) {
        this.output = output;
    }

}
