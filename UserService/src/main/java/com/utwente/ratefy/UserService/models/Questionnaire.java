package com.utwente.ratefy.UserService.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import com.vladmihalcea.hibernate.type.json.JsonStringType;
import lombok.Data;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;
import org.springframework.stereotype.Component;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

@Data
@Component
@TypeDefs({
  @TypeDef(name = "json", typeClass = JsonStringType.class),
  @TypeDef(name = "jsonb", typeClass = JsonBinaryType.class)
})
public class Questionnaire implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Integer id;

  @Column(name = "title")
  private String title;

  @Type(type = "json")
  @Column(name = "content", columnDefinition = "json")
  @JsonProperty("content")
  @NotNull
  @Valid
  private List<QuestionnaireContentItem> content;

  @Column(name = "reward_id")
  @NotNull
  @JsonProperty(value = "reward_id", required = true)
  private Integer rewardId;

  @Column(name = "user_id")
  @NotNull
  @JsonProperty(value = "user_id", required = true)
  private Integer userId;

  public Questionnaire() {}

  public Questionnaire(
      String title,
      @NotNull @Valid List<QuestionnaireContentItem> content,
      @NotNull Integer rewardId,
      @NotNull Integer userId) {
    this.title = title;
    this.content = content;
    this.rewardId = rewardId;
    this.userId = userId;
  }

  public Integer getId() {
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

  public void setId(Integer id) {
    this.id = id;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public void setContent(List<QuestionnaireContentItem> content) {
    this.content = content;
  }

  public void setRewardId(Integer rewardId) {
    this.rewardId = rewardId;
  }

  public Integer getUserId() {
    return userId;
  }

  public void setUserId(Integer userId) {
    this.userId = userId;
  }

  @Override
  public String toString() {
    final StringBuilder sb = new StringBuilder("Questionnaire {");
    sb.append("id=").append(id);
    sb.append(", title='").append(title).append('\'');
    sb.append(", content=").append(content).append('\'');
    sb.append(", user_id=").append(userId).append('\'');
    sb.append(", reward_id=").append(rewardId).append('\'');
    sb.append('}');
    return sb.toString();
  }
}
