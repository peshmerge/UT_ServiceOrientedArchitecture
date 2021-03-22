package com.utwente.ratefy.FeedbackService.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import java.time.Instant;

@Data
public class FeedbackDto {

    private int id;

    @JsonProperty(required = true)
    @NotEmpty
    @NotBlank
    private String content;

    @JsonProperty(required = true)
    @NotNull
    private Integer studentId;

    @JsonProperty(required = true)
    @NotEmpty
    @NotBlank
    private Integer questionnaireId;

    @JsonProperty(required = true)
    @NotNull
    @PastOrPresent
    private Instant createdAt;

    @JsonProperty(required = true)
    private Instant updatedAt;

    public FeedbackDto(int id, @NotEmpty @NotBlank String content, @NotNull Integer studentId, @NotEmpty @NotBlank Integer questionnaireId, @NotNull @PastOrPresent Instant createdAt, Instant updatedAt) {
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
