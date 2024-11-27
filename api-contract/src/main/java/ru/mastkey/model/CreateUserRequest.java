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
 * CreateUserRequest
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2024-11-28T00:27:38.483324+03:00[Europe/Moscow]", comments = "Generator version: 7.10.0")
public class CreateUserRequest {

  private String username;

  private Long telegramUserId;

  private Long chatId;

  public CreateUserRequest() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public CreateUserRequest(String username, Long telegramUserId, Long chatId) {
    this.username = username;
    this.telegramUserId = telegramUserId;
    this.chatId = chatId;
  }

  public CreateUserRequest username(String username) {
    this.username = username;
    return this;
  }

  /**
   * Get username
   * @return username
   */
  @NotNull 
  @Schema(name = "username", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("username")
  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public CreateUserRequest telegramUserId(Long telegramUserId) {
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

  public CreateUserRequest chatId(Long chatId) {
    this.chatId = chatId;
    return this;
  }

  /**
   * Get chatId
   * @return chatId
   */
  @NotNull 
  @Schema(name = "chatId", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("chatId")
  public Long getChatId() {
    return chatId;
  }

  public void setChatId(Long chatId) {
    this.chatId = chatId;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    CreateUserRequest createUserRequest = (CreateUserRequest) o;
    return Objects.equals(this.username, createUserRequest.username) &&
        Objects.equals(this.telegramUserId, createUserRequest.telegramUserId) &&
        Objects.equals(this.chatId, createUserRequest.chatId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(username, telegramUserId, chatId);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class CreateUserRequest {\n");
    sb.append("    username: ").append(toIndentedString(username)).append("\n");
    sb.append("    telegramUserId: ").append(toIndentedString(telegramUserId)).append("\n");
    sb.append("    chatId: ").append(toIndentedString(chatId)).append("\n");
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

