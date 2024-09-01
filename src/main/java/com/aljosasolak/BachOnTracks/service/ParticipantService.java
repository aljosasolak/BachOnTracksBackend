package com.aljosasolak.BachOnTracks.service;

import com.aljosasolak.BachOnTracks.mapper.MapperImpl;
import com.aljosasolak.BachOnTracks.model.Participant;
import com.aljosasolak.BachOnTracks.model.Project;
import com.aljosasolak.BachOnTracks.repository.ParticipantRepository;
import com.aljosasolak.BachOnTracks.repository.ProjectRepository;
import com.aljosasolak.BachOnTracks.resource.FileResource;
import com.aljosasolak.BachOnTracks.resource.ParticipantCreateResource;
import com.aljosasolak.BachOnTracks.resource.ParticipantResource;
import com.aljosasolak.BachOnTracks.resource.ProjectCreateResource;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class ParticipantService {

    private final ParticipantRepository participantRepository;
    private final MapperImpl mapper;
    private final ProjectRepository projectRepository;

    public ParticipantService(ParticipantRepository participantRepository, MapperImpl mapper, ProjectRepository projectRepository) {
        this.participantRepository = participantRepository;
        this.mapper = mapper;
        this.projectRepository = projectRepository;
    }

    public ParticipantResource createParticipant(ParticipantCreateResource participantCreateResource) {
        Participant entity = mapper.map(participantCreateResource, Participant.class);
        Project project = projectRepository.findById(participantCreateResource.getProjectId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        return mapper.map(participantRepository.save(entity), ParticipantResource.class);
    }

    public List<ParticipantResource> getParticipants() {
        Sort sort = Sort.by(Sort.Direction.ASC, "lastname");
        return mapper.mapList(participantRepository.findAll(sort), ParticipantResource.class);
    }

    public List<ParticipantResource> filterByProjectId(String id) {
        Sort sort = Sort.by(Sort.Direction.DESC, "lastname");
        return mapper.mapList(participantRepository.filterByProjectId(id), ParticipantResource.class);
    }

    public ParticipantResource updateParticipant(String id, ParticipantCreateResource participantCreateResource) {
        Participant entity = participantRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        entity.setFirstname(participantCreateResource.getFirstname());
        entity.setLastname(participantCreateResource.getLastname());
        entity.setEmail(participantCreateResource.getEmail());
        entity.setPhoneNumber(participantCreateResource.getPhoneNumber());

        return mapper.map(participantRepository.save(entity), ParticipantResource.class);
    }

    public Boolean deleteParticipant(String id) {
        Participant entity = participantRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        participantRepository.delete(entity);
        return true;
    }
}
