package com.utwente.ratefy.QuestionnaireService.models;

import java.time.Instant;
import java.util.Objects;
import javax.persistence.*;

@Entity
@Table(name = "questionnaires")
public class Questionnaire {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Integer id;

  @Column(name = "title")
  private String title;

  @Column(name = "content")
  private String content;

  @Column(name = "reward_id")
  private Integer rewardId;

  @Column(name = "created_at")
  private Instant createdAt = Instant.now();

  @Column(name = "updated_at")
  private Instant updatedAt;

  public Questionnaire() {}

  /**
   * @param title
   * @param content
   * @param rewardId
   */
  public Questionnaire(String title, String content, Integer rewardId) {
    this.title = title;
    this.content = content;
    this.rewardId = rewardId;
  }

  public Integer getId() {
    return id;
  }

  public String getTitle() {
    return title;
  }

  public String getContent() {
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

  public void setContent(String content) {
    this.content = content;
  }

  public void setRewardId(Integer rewardId) {
    this.rewardId = rewardId;
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
    final Questionnaire other = (Questionnaire) obj;

    if (!Objects.equals(this.title, other.title)) {
      return false;
    }

    if (this.rewardId != other.rewardId) {
      return false;
    }

    return Objects.equals(this.id, other.id);
  }

  @Override
  public String toString() {
    final StringBuilder sb = new StringBuilder("Questionnaire {");
    sb.append("id=").append(id);
    sb.append(", title='").append(title).append('\'');
    sb.append(", content=").append(content).append('\'');
    ;
    sb.append(", reward_id=").append(rewardId).append('\'');
    ;
    sb.append('}');
    return sb.toString();
  }
}
