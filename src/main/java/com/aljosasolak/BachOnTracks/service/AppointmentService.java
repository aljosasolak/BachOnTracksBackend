package com.aljosasolak.BachOnTracks.service;

import com.aljosasolak.BachOnTracks.mapper.MapperImpl;
import com.aljosasolak.BachOnTracks.model.Appointment;
import com.aljosasolak.BachOnTracks.model.Project;
import com.aljosasolak.BachOnTracks.repository.AppointmentRepository;
import com.aljosasolak.BachOnTracks.repository.ProjectRepository;
import com.aljosasolak.BachOnTracks.resource.AppointmentCreateResource;
import com.aljosasolak.BachOnTracks.resource.AppointmentResource;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class AppointmentService {


    private final AppointmentRepository appointmentRepository;
    private final UserService userService;
    private final MapperImpl mapper;
    private final ProjectRepository projectRepository;


    public AppointmentService(AppointmentRepository appointmentRepository, UserService userService, MapperImpl mapper, ProjectRepository projectRepository) {
        this.appointmentRepository = appointmentRepository;
        this.userService = userService;
        this.mapper = mapper;
        this.projectRepository = projectRepository;
    }

    public AppointmentResource createAppointment(AppointmentCreateResource appointmentCreateResource) {
        Appointment entity = mapper.map(appointmentCreateResource, Appointment.class);
        Project project = projectRepository.findById(appointmentCreateResource.getProjectId()).orElse(null);
        entity.setProject(project);
        return mapper.map(appointmentRepository.save(entity), AppointmentResource.class);
    }


    public List<AppointmentResource> getAppointments() {
        Sort sort = Sort.by(Sort.Direction.ASC, "date", "startingTime");
        return mapper.mapList(appointmentRepository.findAll(sort), AppointmentResource.class);
    }

    public List<AppointmentResource> filterByProjectId(String projectId) {
        // Sort sort = Sort.by(Sort.Direction.DESC,  "date","startingTime");
        return mapper.mapList(appointmentRepository.filterByProjectId(projectId), AppointmentResource.class);
    }

    public AppointmentResource updateAppointment(String id, AppointmentCreateResource appointmentCreateResource) {
        Appointment entity = appointmentRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        entity.setDate(appointmentCreateResource.getDate());
        entity.setStartingTime(appointmentCreateResource.getStartingTime());
        entity.setEndingTime(appointmentCreateResource.getEndingTime());
        entity.setLocation(appointmentCreateResource.getLocation());
        entity.setType(appointmentCreateResource.getType());
        entity.setStatus(appointmentCreateResource.getStatus());

        return mapper.map(appointmentRepository.save(entity), AppointmentResource.class);
    }

    public Boolean deleteAppointment(String id) {
        Appointment entity = appointmentRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        appointmentRepository.delete(entity);
        return true;
    }



}
