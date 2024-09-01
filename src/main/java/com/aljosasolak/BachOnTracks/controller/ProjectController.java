package com.aljosasolak.BachOnTracks.controller;

import com.aljosasolak.BachOnTracks.resource.ParticipantResource;
import com.aljosasolak.BachOnTracks.resource.ProjectCreateResource;
import com.aljosasolak.BachOnTracks.resource.ProjectResource;
import com.aljosasolak.BachOnTracks.service.ParticipantService;
import com.aljosasolak.BachOnTracks.service.ProjectService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/projects/")
public class ProjectController {

    private final ProjectService projectService;

    public ProjectController(ProjectService projectService, ParticipantService participantService) {
        this.projectService = projectService;
    }
    @PostMapping("")
    ProjectResource createProject(@RequestBody ProjectCreateResource projectCreateResource) {
        return this.projectService.createProject(projectCreateResource);
    }

    @GetMapping("")
    List<ProjectResource> getProjects() {
        return this.projectService.getProjects();
    }

    @GetMapping("_filtered/{id}")
    List<ProjectResource> filterByUserId(@PathVariable String id) {
        return this.projectService.filterByUserId(id);
    }

    @GetMapping("{id}")
    ProjectResource getProject(@PathVariable String id) {
        return this.projectService.getProject(id);
    }

    @PutMapping("{id}")
    ProjectResource updateProject(@PathVariable String id, @RequestBody ProjectCreateResource projectUpdateResource) {
        return this.projectService.updateProject(id, projectUpdateResource);
    }

    @DeleteMapping("{id}")
    Boolean deleteProject(@PathVariable String id) {
        return this.projectService.deleteProject(id);
    }


}
