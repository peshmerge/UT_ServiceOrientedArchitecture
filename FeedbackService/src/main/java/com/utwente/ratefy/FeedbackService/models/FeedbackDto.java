package com.utwente.ratefy.FeedbackService.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import java.time.Instant;
import java.util.List;

@Data
public class FeedbackDto {

    @JsonProperty("id")
    private int id;

    @JsonProperty("content")
    @NotEmpty
    @NotBlank
    private List<FeedbackContentItem> content;

    @JsonProperty("student_id")
    @NotNull
    private Integer studentId;

    @JsonProperty("questionnaire_id")
    @NotEmpty
    @NotBlank
    private Integer questionnaireId;

    @JsonProperty("created_at")
    @NotNull
    @PastOrPresent
    private Instant createdAt;

    @JsonProperty("updated_at")
    private Instant updatedAt;

    public FeedbackDto(int id, @NotEmpty @NotBlank List<FeedbackContentItem> content, @NotNull Integer studentId, @NotEmpty @NotBlank Integer questionnaireId, @NotNull @PastOrPresent Instant createdAt, Instant updatedAt) {
        this.id = id;
        this.content = content;
        this.studentId = studentId;
        this.questionnaireId = questionnaireId;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Feedback {");
        sb.append(", content=").append(content).append('\'');
        sb.append(", studentId=").append(studentId).append('\'');
        sb.append(", questionnaireId=").append(questionnaireId).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
