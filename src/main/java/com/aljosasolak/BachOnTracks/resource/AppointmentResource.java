package com.aljosasolak.BachOnTracks.resource;

import com.aljosasolak.BachOnTracks.Enum.AppointmentStatus;
import com.aljosasolak.BachOnTracks.Enum.AppointmentType;
import com.aljosasolak.BachOnTracks.model.Project;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;


public class AppointmentResource {

    private String id;
    private LocalDate date;
    private LocalTime startingTime;
    private LocalTime endingTime;
    private AppointmentStatus status;
    private String location;
    private AppointmentType type;
    private Project project;

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
