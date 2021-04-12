package com.utwente.ratefy.StudentService.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.Instant;

@Data
@Entity
@Table(name = "students")
public class Student {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Integer id;

  @Column(name = "student_number")
  @NotBlank
  @JsonProperty(value = "student_number", required = true)
  private Integer studentNumber;

  @Column(name = "name")
  @NotBlank
  @JsonProperty(value = "name", required = true)
  private String name;

  @Column(name = "email")
  @NotBlank
  @JsonProperty(value = "email", required = true)
  private String email;

  /**
   * the value is the summation of all values from reward service for each given feedback on a
   * questionnaire*
   */
  @Column(name = "points")
  @NotBlank
  @JsonProperty(value = "points", required = true)
  private Integer points;

  @Column(name = "opt_in")
  @NotBlank
  @JsonProperty(value = "opt-in", required = true)
  private Boolean optIn;

  @CreationTimestamp
  @Column(name = "created_at", updatable = false, nullable = false)
  @JsonProperty("created_at")
  private Instant createdAt = Instant.now();

  @Column(name = "updated_at")
  @JsonProperty("updated_at")
  private Instant updatedAt;

  public Student(
      Integer id,
      Integer studentNumber,
      String name,
      String email,
      Integer points,
      Instant createdAt) {
    this.id = id;
    this.studentNumber = studentNumber;
    this.name = name;
    this.email = email;
    this.points = points;
    this.createdAt = createdAt;
  }

  public Student() {}

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public Integer getStudentNumber() {
    return studentNumber;
  }

  public void setStudentNumber(Integer studentNumber) {
    this.studentNumber = studentNumber;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public Integer getPoints() {
    return points;
  }

  public void setPoints(Integer points) {
    this.points = points;
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

  public Boolean getOptIn() {
    return optIn;
  }

  public void setOptIn(Boolean optIn) {
    this.optIn = optIn;
  }

  @Override
  public String toString() {
    final StringBuilder sb = new StringBuilder("Student {");
    sb.append("id=").append(id);
    sb.append(", name=").append(name).append('\'');
    sb.append(", student_number=").append(studentNumber).append('\'');
    sb.append(", email=").append(email).append('\'');
    sb.append(", points=").append(points).append('\'');
    sb.append('}');
    return sb.toString();
  }
}
