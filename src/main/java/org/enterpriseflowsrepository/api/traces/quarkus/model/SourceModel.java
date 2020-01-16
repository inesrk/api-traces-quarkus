
package org.enterpriseflowsrepository.api.traces.quarkus.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class SourceModel {

  @JsonProperty("protocol")
  private String protocol;

  @JsonProperty("source")
  private String source;

  @JsonProperty("protocol")
  public String getProtocol() {
    return protocol;
  }

  @JsonProperty("protocol")
  public void setProtocol(String protocol) {
    this.protocol = protocol;
  }

  @JsonProperty("source")
  public String getSource() {
    return source;
  }

  @JsonProperty("source")
  public void setSource(String source) {
    this.source = source;
  }
}
