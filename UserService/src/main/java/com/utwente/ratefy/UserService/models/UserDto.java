package com.utwente.ratefy.UserService.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import java.time.Instant;

@Data
public class UserDto {

  @JsonProperty("id")
  private int id;

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

  @JsonProperty("created_at")
  @NotNull
  @PastOrPresent
  private Instant createdAt;

  @JsonProperty("updated_at")
  private Instant updatedAt;

  public UserDto(int id, String name, String email, Integer createdBy, Instant createdAt, Instant updatedAt) {
    this.id = id;
    this.name = name;
    this.email = email;
    this.createdBy = createdBy;
    this.createdAt = createdAt;
    this.updatedAt = updatedAt;
  }

  public UserDto() {

  }
  public int getId() {
    return id;
  }

  public void setId(int id) {
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
