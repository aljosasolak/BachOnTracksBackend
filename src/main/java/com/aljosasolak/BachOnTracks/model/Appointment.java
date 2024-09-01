package com.aljosasolak.BachOnTracks.model;

import com.aljosasolak.BachOnTracks.Enum.AppointmentStatus;
import com.aljosasolak.BachOnTracks.Enum.AppointmentType;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

@Entity
@Table(name = "appointments")
public class Appointment {

    @Id
    private String id;
    @PrePersist
    public void generateId() {this.id = UUID.randomUUID().toString();}
    private LocalDate date;
    private LocalTime startingTime;
    private LocalTime endingTime;
    @Enumerated(EnumType.STRING)
    private AppointmentStatus status;
    @Column(name = "project_id", insertable = false, updatable = false)
    private String projectId;
    @ManyToOne
    @JoinColumn(name = "project_id", referencedColumnName = "id")
    private Project project;
    private String location;
    @Enumerated(EnumType.STRING)
    private AppointmentType type;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getStartingTime() {
        return startingTime;
    }

    public void setStartingTime(LocalTime startingTime) {
        this.startingTime = startingTime;
    }

    public LocalTime getEndingTime() {
        return endingTime;
    }

    public void setEndingTime(LocalTime endingTime) {
        this.endingTime = endingTime;
    }

    public AppointmentStatus getStatus() {
        return status;
    }

    public void setStatus(AppointmentStatus status) {
        this.status = status;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public AppointmentType getType() {
        return type;
    }

    public void setType(AppointmentType type) {
        this.type = type;
    }
}
