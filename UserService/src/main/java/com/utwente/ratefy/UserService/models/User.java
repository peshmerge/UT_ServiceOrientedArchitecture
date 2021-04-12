package com.utwente.ratefy.UserService.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.Instant;

@Data
@Entity
@Table(name = "users")
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Integer id;

  @Column(name = "name")
  @NotBlank
  @JsonProperty(value = "name", required = true)
  private String name;

  @Column(name = "email")
  @NotBlank
  @JsonProperty(value = "email", required = true)
  private String email;

  @Column(name = "created_by")
  @NotBlank
  @JsonProperty(value = "created_by", required = true)
  private Integer createdBy;

  @CreationTimestamp
  @Column(name = "created_at", updatable = false, nullable = false)
  @JsonProperty("created_at")
  private Instant createdAt = Instant.now();

  @Column(name = "updated_at")
  @JsonProperty("updated_at")
  private Instant updatedAt;

  public User(String name, String email, Integer createdBy, Instant createdAt) {
    this.name = name;
    this.email = email;
    this.createdBy = createdBy;
    this.createdAt = createdAt;
  }

  public User() {

  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
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

  public Integer getCreatedBy() {
    return createdBy;
  }

  public void setCreatedBy(Integer createdBy) {
    this.createdBy = createdBy;
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
  public String toString() {
    final StringBuilder sb = new StringBuilder("User {");
    sb.append("id=").append(id);
    sb.append(", name=").append(name).append('\'');
    sb.append(", email=").append(email).append('\'');
    sb.append('}');
    return sb.toString();
  }
}
