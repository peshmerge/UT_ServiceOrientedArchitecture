package com.utwente.ratefy.FeedbackService.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import com.vladmihalcea.hibernate.type.json.JsonStringType;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;

import java.time.Instant;
import java.util.List;
import java.util.Objects;
import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Data
@Entity
@Table(name = "feedbacks")
@TypeDefs({
        @TypeDef(name = "json", typeClass = JsonStringType.class),
        @TypeDef(name = "jsonb", typeClass = JsonBinaryType.class)
})
public class Feedback {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Type(type = "json")
    @Column(name = "content", columnDefinition = "json")
    @JsonProperty("content")
    @NotNull
    @Valid
    private List<FeedbackContentItem> content;

    @Column(name = "student_id")
    @NotNull
    @JsonProperty(value ="student_id", required = true)
    private Integer studentId;

    @Column(name = "questionnaire_id")
    @NotNull
    @JsonProperty(value = "questionnaire_id", required = true)
    private Integer questionnaireId;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false, nullable = false)
    @JsonProperty("created_at")
    private Instant createdAt = Instant.now();

    @Column(name = "updated_at")
    @JsonProperty("updated_at")
    private Instant updatedAt;

    public Feedback() {
    }

    public Feedback(List<FeedbackContentItem> content, Integer studentId, Integer questionnaireId, Instant createdAt) {
        this.content = content;
        this.studentId = studentId;
        this.questionnaireId = questionnaireId;
        this.createdAt = createdAt;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<FeedbackContentItem> getContent() {
        return content;
    }

    public void setContent(List<FeedbackContentItem> content) {
        this.content = content;
    }

    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    public Integer getQuestionnaireId() {
        return questionnaireId;
    }

    public void setQuestionnaireId(Integer questionnaireId) {
        this.questionnaireId = questionnaireId;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Instant updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Feedback other = (Feedback) obj;

        if (!Objects.equals(this.studentId, other.studentId)) {
            return false;
        }

        if (!Objects.equals(this.content, other.content)) {
            return false;
        }

        return Objects.equals(this.id, other.id);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Feedback {");
        sb.append("id=").append(id);
        sb.append(", content=").append(content).append('\'');
        sb.append(", studentId=").append(studentId).append('\'');
        sb.append(", questionnaireId=").append(questionnaireId).append('\'');

        sb.append('}');
        return sb.toString();
    }
}
