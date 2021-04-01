package com.utwente.ratefy.ScheduleService.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import java.time.Instant;

@Data
public class ScheduleDto {

    @JsonProperty("id")
    private int id;

    @JsonProperty("student_id")
    @NotEmpty
    @NotBlank
    private Integer studentId;

    @JsonProperty("event_name")
    @NotNull
    private String eventName;

    @JsonProperty("course_name")
    @NotNull
    private String courseName;

    @JsonProperty("start_time")
    @NotNull
    private Instant startTime;

    @JsonProperty("end_time")
    @NotNull
    private Instant endTime;

    @JsonProperty("created_at")
    @NotNull
    @PastOrPresent
    private Instant createdAt;

    @JsonProperty("updated_at")
    private Instant updatedAt;

    public int getId() {
        return id;
    }

    public Integer getStudentId() {
        return studentId;
    }

    public String getEventName() {
        return eventName;
    }

    public String getCourseName() {
        return courseName;
    }

    public Instant getStartTime() {
        return startTime;
    }

    public Instant getEndTime() {
        return endTime;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public void setStartTime(Instant startTime) {
        this.startTime = startTime;
    }

    public void setEndTime(Instant endTime) {
        this.endTime = endTime;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    public void setUpdatedAt(Instant updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Schedule {");
        sb.append(", student_id=").append(studentId).append('\'');
        sb.append(", event_name=").append(eventName).append('\'');
        sb.append(", course_name=").append(courseName).append('\'');
        sb.append(", start_time=").append(startTime).append('\'');
        sb.append(", end_time=").append(endTime).append('\'');
        sb.append(", created_at=").append(createdAt).append('\'');
        sb.append(", updated_at=").append(updatedAt).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
