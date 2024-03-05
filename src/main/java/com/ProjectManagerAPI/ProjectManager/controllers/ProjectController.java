package com.ProjectManagerAPI.ProjectManager.controllers;

import com.ProjectManagerAPI.ProjectManager.domain.dto.ProjectDto;
import com.ProjectManagerAPI.ProjectManager.domain.entities.ProjectEntity;
import com.ProjectManagerAPI.ProjectManager.mappers.Mapper;
import com.ProjectManagerAPI.ProjectManager.services.ProjectService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
public class ProjectController {

    private final ProjectService projectService;
    private final Mapper<ProjectEntity, ProjectDto> projectMapper;

    public ProjectController(ProjectService projectService, Mapper<ProjectEntity, ProjectDto> projectMapper) {
        this.projectService = projectService;
        this.projectMapper = projectMapper;
    }

    @GetMapping(path = "/projects")
    public List<ProjectDto> listProjects() {
        List<ProjectEntity> projects = projectService.findAll();
        return projects.stream().map(projectMapper::mapTo).collect(Collectors.toList());
    }

    @PostMapping(path = "/projects")
    public ResponseEntity<ProjectDto> createProject(@RequestBody ProjectDto projectDto) {
        ProjectEntity projectEntity = projectMapper.mapFrom(projectDto);
        ProjectEntity savedProjectEntity = projectService.save(projectEntity);
        return new ResponseEntity<>(projectMapper.mapTo(savedProjectEntity), HttpStatus.CREATED);
    }

    @GetMapping(path = "/projects/{projectId}")
    public ResponseEntity<ProjectDto> getProjectById(@PathVariable Long projectId) {
        Optional<ProjectEntity> foundProject = projectService.findOne(projectId);
        return foundProject.map(
                ProjectEntity -> {
                    ProjectDto projectDto = projectMapper.mapTo(ProjectEntity);
                    return new ResponseEntity<>(projectDto, HttpStatus.OK);
                }
        ).orElse(
                new ResponseEntity<>(HttpStatus.NOT_FOUND)
        );
    }

    @PutMapping(path = "/projects/{projectId}")
    public ResponseEntity<ProjectDto> fullUpdateProject(@PathVariable("projectId") Long projectId, @RequestBody ProjectDto projectDto) {

        if (!projectService.doesProjectExists(projectId)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        projectDto.setProjectId(projectId);
        ProjectEntity projectEntity = projectMapper.mapFrom(projectDto);
        ProjectEntity savedProjectEntity = projectService.save(projectEntity);

        return new ResponseEntity<>(
                projectMapper.mapTo(savedProjectEntity),
                HttpStatus.OK
        );
    }

    @DeleteMapping(path = "/projects/{projectId}")
    public ResponseEntity<Void> deleteProjectById(@PathVariable("projectId") Long projectId) {
        projectService.deleteById(projectId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
