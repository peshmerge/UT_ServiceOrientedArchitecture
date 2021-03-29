package com.utwente.ratefy.RewardService.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import java.time.Instant;

@Data
public class RewardDto {

    @JsonProperty("id")
    private int id;

    @JsonProperty("title")
    @NotEmpty
    @NotBlank
    private String title;

    @JsonProperty("value")
    @NotNull
    private Integer value;

    @JsonProperty("created_at")
    @NotNull
    @PastOrPresent
    private Instant createdAt;

    @JsonProperty("updated_at")
    private Instant updatedAt;

    public RewardDto(
            int id,
            @NotEmpty @NotBlank String title,
            @NotNull Integer value,
            @NotNull @PastOrPresent Instant createdAt,
            Instant updatedAt
    ) {
        this.id = id;
        this.title = title;
        this.value = value;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public Integer getValue() {
        return value;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Reward {");
        sb.append("id=").append(id).append('\'');
        sb.append(", title=").append(title).append('\'');
        sb.append(", value=").append(value).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
