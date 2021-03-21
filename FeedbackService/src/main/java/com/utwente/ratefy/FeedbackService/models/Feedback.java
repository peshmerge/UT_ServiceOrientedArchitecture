package com.utwente.ratefy.FeedbackService.models;

import java.time.Instant;
import java.util.Objects;
import javax.persistence.*;

@Entity
@Table(name = "feedbacks")
public class Feedback {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Integer id;

  @Column(name = "content")
  private String content;

  @Column(name = "student_id")
  private Integer studentId;

  @Column(name = "questionnaire_id")
  private Integer questionnaireId;

  @Column(name = "created_at")
  private Instant createdAt = Instant.now();

  @Column(name = "updated_at")
  private Instant updatedAt;

  public Feedback() {}

  public Feedback(String content, Integer studentId, Integer questionnaireId, Instant createdAt) {
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

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
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
