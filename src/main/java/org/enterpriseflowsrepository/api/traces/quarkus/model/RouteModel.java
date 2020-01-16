
package org.enterpriseflowsrepository.api.traces.quarkus.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class RouteModel {

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
   * 
   * <p>source for Input or Output.
   */
  @JsonProperty("input")
  @JsonPropertyDescription("source for Input or Output.")
  private SourceModel input;

  /**
   * Root Type for Source
   * 
   * <p>source for Input or Output.
   */
  @JsonProperty("output")
  @JsonPropertyDescription("source for Input or Output.")
  private SourceModel output;

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
   * Source for Input or Output.
   */
  @JsonProperty("input")
  public SourceModel getInput() {
    return input;
  }

  /**
   * Source for Input or Output.
   */
  @JsonProperty("input")
  public void setInput(SourceModel input) {
    this.input = input;
  }

  /**
   * Source for Input or Output.
   */
  @JsonProperty("output")
  public SourceModel getOutput() {
    return output;
  }

  /**
   * Source for Input or Output.
   */
  @JsonProperty("output")
  public void setOutput(SourceModel output) {
    this.output = output;
  }
}
