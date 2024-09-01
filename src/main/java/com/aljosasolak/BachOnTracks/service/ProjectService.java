package com.aljosasolak.BachOnTracks.service;

import com.aljosasolak.BachOnTracks.mapper.MapperImpl;
import com.aljosasolak.BachOnTracks.model.Project;
import com.aljosasolak.BachOnTracks.model.User;
import com.aljosasolak.BachOnTracks.repository.ProjectRepository;
import com.aljosasolak.BachOnTracks.resource.ProjectCreateResource;
import com.aljosasolak.BachOnTracks.resource.ProjectResource;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class ProjectService {

    private final ProjectRepository projectRepository;
    private final UserService userService;
    private final MapperImpl mapper;


    public ProjectService(ProjectRepository repository, UserService userService, MapperImpl mapper) {
        this.projectRepository = repository;
        this.userService = userService;
        this.mapper = mapper;
    }

    public ProjectResource createProject(ProjectCreateResource projectCreateResource) {
        Project entity = mapper.map(projectCreateResource, Project.class);
        User dummyUser = userService.getDummyUser();
        entity.setUserId(dummyUser.getId());
        entity.setUser(dummyUser);

        return mapper.map(projectRepository.save(entity), ProjectResource.class);
    }


    public List<ProjectResource> getProjects() {
        Sort sort = Sort.by(Sort.Direction.ASC, "startingDate");
        return mapper.mapList(projectRepository.findAll(sort), ProjectResource.class);
    }

    public ProjectResource getProject(String id) {
        Project entity = projectRepository.findById(id).orElseThrow(() ->new ResponseStatusException(HttpStatus.NOT_FOUND));
        return mapper.map(entity, ProjectResource.class);
    }

    public List<ProjectResource> filterByUserId(String id) {
        Sort sort = Sort.by(Sort.Direction.ASC, "startingDate");
        return mapper.mapList(projectRepository.filterByUserId(id), ProjectResource.class);
    }

    public ProjectResource updateProject (String id, ProjectCreateResource projectCreateResource) {
        Project entity = projectRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        entity.setTitle(projectCreateResource.getTitle());
        entity.setDescription(projectCreateResource.getDescription());
        entity.setStartingDate(projectCreateResource.getStartingDate());
        entity.setEndingDate(projectCreateResource.getEndingDate());
        User dummyUser = userService.getDummyUser();
        // entity.setUserId(dummyUser.getId());
        entity.setUser(dummyUser);
        return mapper.map(projectRepository.save(entity), ProjectResource.class);
    }

    public Boolean deleteProject(String id) {
        Project entity = projectRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        projectRepository.delete(entity);
        return true;
    }


}
