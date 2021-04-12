package com.utwente.ratefy.ScheduleService.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.Instant;

@Data
@Entity
@Table(name = "schedules")
public class Schedule {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "student_id")
    @NotNull
    @JsonProperty(value = "student_id", required = true)
    private Integer studentId;

    @Column(name = "event_name")
    @NotNull
    @JsonProperty(value = "event_name", required = true)
    private String eventName;

    @Column(name = "course_name")
    @NotNull
    @JsonProperty(value = "course_name", required = true)
    private String courseName;

    @Column(name = "start_time", nullable = false)
    @JsonProperty("start_time")
    private Instant startTime;

    @Column(name = "end_time", nullable = false)
    @JsonProperty("end_time")
    private Instant endTime;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false, nullable = false)
    @JsonProperty("created_at")
    private Instant createdAt = Instant.now();

    @Column(name = "updated_at")
    @JsonProperty("updated_at")
    private Instant updatedAt;

    public Schedule() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public Instant getStartTime() {
        return startTime;
    }

    public void setStartTime(Instant startTime) {
        this.startTime = startTime;
    }

    public Instant getEndTime() {
        return endTime;
    }

    public void setEndTime(Instant endTime) {
        this.endTime = endTime;
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
        final StringBuilder sb = new StringBuilder("Schedule {");
        sb.append("id=").append(id);
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
