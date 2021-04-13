package com.utwente.ratefy.UserService.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import java.time.Instant;
import java.util.List;

@Data
public class QuestionnaireDto {

  @JsonProperty("id")
  private int id;

  @Column(name = "title")
  @NotEmpty
  @NotBlank
  private String title;

  @JsonProperty("content")
  @NotEmpty
  @NotBlank
  private List<QuestionnaireContentItem> content;

  @JsonProperty("reward_id")
  @NotNull
  private Integer rewardId;

  @JsonProperty("user_id")
  @NotEmpty
  @NotBlank
  private Integer userId;

  @JsonProperty("created_at")
  @NotNull
  @PastOrPresent
  private Instant createdAt;

  @JsonProperty("updated_at")
  private Instant updatedAt;

  public QuestionnaireDto(
      int id,
      @NotEmpty @NotBlank String title,
      @NotEmpty @NotBlank List<QuestionnaireContentItem> content,
      @NotNull Integer rewardId,
      @NotEmpty @NotBlank Integer userId,
      @NotNull @PastOrPresent Instant createdAt,
      @PastOrPresent Instant updatedAt) {
    this.id = id;
    this.title = title;
    this.content = content;
    this.rewardId = rewardId;
    this.userId = userId;
    this.createdAt = createdAt;
    this.updatedAt = updatedAt;
  }

  @Override
  public String toString() {
    final StringBuilder sb = new StringBuilder("Questionnaire {");
    sb.append(", content=").append(content).append('\'');
    sb.append(", reward_id=").append(rewardId).append('\'');
    sb.append(", user_id=").append(userId).append('\'');
    sb.append(", created_at=").append(createdAt).append('\'');
    sb.append(", updated_at=").append(updatedAt).append('\'');
    sb.append('}');
    return sb.toString();
  }

  public int getId() {
    return id;
  }

  public String getTitle() {
    return title;
  }

  public List<QuestionnaireContentItem> getContent() {
    return content;
  }

  public Integer getRewardId() {
    return rewardId;
  }

  public Integer getUserId() {
    return userId;
  }

  public Instant getCreatedAt() {
    return createdAt;
  }

  public Instant getUpdatedAt() {
    return updatedAt;
  }
}
