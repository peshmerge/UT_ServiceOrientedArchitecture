package com.utwente.ratefy.UserService.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@Component
public class QuestionnaireContentItem implements Serializable {

  @JsonProperty("question_id")
  @NotNull
  private Integer questionId;

  @JsonProperty(required = true)
  private String question;

  public Integer getQuestionId() {
    return questionId;
  }

  public void setQuestionId(Integer questionId) {
    this.questionId = questionId;
  }

  public String getQuestion() {
    return question;
  }

  public void setQuestion(String question) {
    this.question = question;
  }

  public QuestionnaireContentItem(Integer questionId, String question) {
    this.questionId = questionId;
    this.question = question;
  }

  public QuestionnaireContentItem() {}

  @Override
  public String toString() {
    final StringBuilder sb = new StringBuilder("{");
    sb.append("question_id=").append(questionId).append('\'');
    sb.append(", question=").append(question).append('\'');
    sb.append('}');
    return sb.toString();
  }
}
