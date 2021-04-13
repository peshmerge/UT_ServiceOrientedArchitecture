package com.utwente.ratefy.StudentService.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Component
@Data
public class FeedbackContentItem implements Serializable {

  @JsonProperty("question_id")
  @NotNull
  private Integer questionId;

  @JsonProperty(required = true)
  private String answer;

  public Integer getQuestionId() {
    return questionId;
  }

  public void setQuestionId(Integer questionId) {
    this.questionId = questionId;
  }

  public String getAnswer() {
    return answer;
  }

  public void setAnswer(String answer) {
    this.answer = answer;
  }

  public FeedbackContentItem(Integer questionId, String answer) {
    this.questionId = questionId;
    this.answer = answer;
  }

  public FeedbackContentItem() {}

  @Override
  public String toString() {
    final StringBuilder sb = new StringBuilder("{");
    sb.append("question_id=").append(questionId).append('\'');
    sb.append(", answer=").append(answer).append('\'');
    sb.append('}');
    return sb.toString();
  }
}
