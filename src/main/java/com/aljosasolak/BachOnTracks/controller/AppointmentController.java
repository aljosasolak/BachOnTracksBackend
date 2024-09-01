package com.aljosasolak.BachOnTracks.controller;

import com.aljosasolak.BachOnTracks.resource.AppointmentCreateResource;
import com.aljosasolak.BachOnTracks.resource.AppointmentResource;
import com.aljosasolak.BachOnTracks.service.AppointmentService;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/api/appointments/")
public class AppointmentController {

    private final AppointmentService appointmentService;


    public AppointmentController(AppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }

    @PostMapping("")
    AppointmentResource createAppointment(@RequestBody AppointmentCreateResource appointmentCreateResource) {
        return this.appointmentService.createAppointment(appointmentCreateResource);
    }
/*
    @GetMapping("")
    List<AppointmentResource> getAppointments() {
        return this.appointmentService.getAppointments();
    }

    // DO YOU NEED getAppointment()?
*/
    @GetMapping("{id}")
    List<AppointmentResource> getAppointments(@PathVariable String id) {
        return this.appointmentService.filterByProjectId(id);
    }

    @PutMapping("{id}")
    AppointmentResource updateAppointment(@PathVariable String id, @RequestBody AppointmentCreateResource appointmentUpdateResource) {
        return this.appointmentService.updateAppointment(id, appointmentUpdateResource);
    }
    @DeleteMapping("{id}")
    Boolean deleteAppointment(@PathVariable String id) {
        return this.appointmentService.deleteAppointment(id);
    }


}

