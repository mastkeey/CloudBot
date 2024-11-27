package ru.mastkey.model;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import java.util.UUID;
import java.time.OffsetDateTime;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import jakarta.annotation.Generated;

/**
 * WorkspaceResponse
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2024-11-28T00:27:38.483324+03:00[Europe/Moscow]", comments = "Generator version: 7.10.0")
public class WorkspaceResponse {

  private UUID workspaceId;

  private String name;

  private Long telegramUserId;

  public WorkspaceResponse workspaceId(UUID workspaceId) {
    this.workspaceId = workspaceId;
    return this;
  }

  /**
   * Get workspaceId
   * @return workspaceId
   */
  @Valid 
  @Schema(name = "workspaceId", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("workspaceId")
  public UUID getWorkspaceId() {
    return workspaceId;
  }

  public void setWorkspaceId(UUID workspaceId) {
    this.workspaceId = workspaceId;
  }

  public WorkspaceResponse name(String name) {
    this.name = name;
    return this;
  }

  /**
   * Get name
   * @return name
   */
  
  @Schema(name = "name", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("name")
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public WorkspaceResponse telegramUserId(Long telegramUserId) {
    this.telegramUserId = telegramUserId;
    return this;
  }

  /**
   * Get telegramUserId
   * @return telegramUserId
   */
  
  @Schema(name = "telegramUserId", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
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
    WorkspaceResponse workspaceResponse = (WorkspaceResponse) o;
    return Objects.equals(this.workspaceId, workspaceResponse.workspaceId) &&
        Objects.equals(this.name, workspaceResponse.name) &&
        Objects.equals(this.telegramUserId, workspaceResponse.telegramUserId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(workspaceId, name, telegramUserId);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class WorkspaceResponse {\n");
    sb.append("    workspaceId: ").append(toIndentedString(workspaceId)).append("\n");
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

