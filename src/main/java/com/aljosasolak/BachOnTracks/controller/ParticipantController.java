package com.aljosasolak.BachOnTracks.controller;

import com.aljosasolak.BachOnTracks.resource.FileResource;
import com.aljosasolak.BachOnTracks.resource.ParticipantCreateResource;
import com.aljosasolak.BachOnTracks.resource.ParticipantResource;
import com.aljosasolak.BachOnTracks.service.ParticipantService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/participants/")
public class ParticipantController {

    private final ParticipantService participantService;

    public ParticipantController(ParticipantService participantService) {
        this.participantService = participantService;
    }

/*
    @GetMapping("")
    List<ParticipantResource> getParticipants() {
        return participantService.getParticipants();
    }
*/
    @PostMapping("")
    ParticipantResource createParticipant(@RequestBody ParticipantCreateResource participantCreateResource) {
        return participantService.createParticipant(participantCreateResource);
    }

    @GetMapping("{id}")
    List<ParticipantResource> getParticipants(@PathVariable String id) {
        return this.participantService.filterByProjectId(id);
    }

    @PutMapping("{id}")
    ParticipantResource updateParticipant(@PathVariable String id, @RequestBody ParticipantCreateResource participantCreateResource) {
        return this.participantService.updateParticipant(id, participantCreateResource);
    }

    @DeleteMapping("{id}")
    boolean deleteParticipant(@PathVariable String id) {
        return this.participantService.deleteParticipant(id);
    }
}
