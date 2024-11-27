package ru.mastkey.model;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import java.time.OffsetDateTime;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import jakarta.annotation.Generated;

/**
 * CreateWorkspaceRequest
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2024-11-28T00:27:38.483324+03:00[Europe/Moscow]", comments = "Generator version: 7.10.0")
public class CreateWorkspaceRequest {

  private String name;

  private Long telegramUserId;

  public CreateWorkspaceRequest() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public CreateWorkspaceRequest(String name, Long telegramUserId) {
    this.name = name;
    this.telegramUserId = telegramUserId;
  }

  public CreateWorkspaceRequest name(String name) {
    this.name = name;
    return this;
  }

  /**
   * Get name
   * @return name
   */
  @NotNull 
  @Schema(name = "name", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("name")
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public CreateWorkspaceRequest telegramUserId(Long telegramUserId) {
    this.telegramUserId = telegramUserId;
    return this;
  }

  /**
   * Get telegramUserId
   * @return telegramUserId
   */
  @NotNull 
  @Schema(name = "telegramUserId", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("telegramUserId")
  public Long getTelegramUserId() {
    return telegramUserId;
  }

  public void setTelegramUserId(Long telegramUserId) {
    this.telegramUserId = telegramUserId;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    CreateWorkspaceRequest createWorkspaceRequest = (CreateWorkspaceRequest) o;
    return Objects.equals(this.name, createWorkspaceRequest.name) &&
        Objects.equals(this.telegramUserId, createWorkspaceRequest.telegramUserId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(name, telegramUserId);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class CreateWorkspaceRequest {\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    telegramUserId: ").append(toIndentedString(telegramUserId)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}

