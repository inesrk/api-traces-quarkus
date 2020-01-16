
package org.enterpriseflowsrepository.api.traces.quarkus.beans;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

/**
 * Root Type for Exception
 * 
 * <p>Exception occurs during transport.
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "code", "class", "detail", "stacktrace" })
public class Exception {

  /**
   * Unique code for this exception.
   */
  @JsonProperty("code")
  @JsonPropertyDescription("Unique code for this exception.")
  private String code;

  /**
   * Class name.
   */
  @JsonProperty("class")
  @JsonPropertyDescription("Class name.")
  private String className;
  
  /**
   * A short text details.
   */
  @JsonProperty("detail")
  @JsonPropertyDescription("A short text details.")
  private String detail;
  
  /**
   * complete stacktrace of this exception.
   */
  @JsonProperty("stacktrace")
  @JsonPropertyDescription("complete stacktrace of this exception.")
  private String stacktrace;

  /**
   * Default constructor.
   */
  public Exception() {
    // nothing here
  }
  
  /**
   * Internal constructor, to be used in tests.
   */
  Exception(String code, String className, String detail, String stacktrace) {
    this.code = code;
    this.className = className;
    this.detail = detail;
    this.stacktrace = stacktrace;
  }

  /**
   * Unique code for this exception.
   */
  @JsonProperty("code")
  public String getCode() {
    return code;
  }

  /**
   * Unique code for this exception.
   */
  @JsonProperty("code")
  public void setCode(String code) {
    this.code = code;
  }

  /**
   * Class name.
   */
  @JsonProperty("class")
  public String getClass_() {
    return className;
  }

  /**
   * Class name.
   */
  @JsonProperty("class")
  public void setClass_(String className) {
    this.className = className;
  }

  /**
   * A short text details.
   */
  @JsonProperty("detail")
  public String getDetail() {
    return detail;
  }

  /**
   * A short text details.
   */
  @JsonProperty("detail")
  public void setDetail(String detail) {
    this.detail = detail;
  }

  /**
   * complete stacktrace of this exception.
   */
  @JsonProperty("stacktrace")
  public String getStacktrace() {
    return stacktrace;
  }

  /**
   * complete stacktrace of this exception.
   */
  @JsonProperty("stacktrace")
  public void setStacktrace(String stacktrace) {
    this.stacktrace = stacktrace;
  }
}
