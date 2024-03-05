package com.ProjectManagerAPI.ProjectManager.controllers;

import com.ProjectManagerAPI.ProjectManager.domain.dto.ProjectDto;
import com.ProjectManagerAPI.ProjectManager.domain.entities.ProjectEntity;
import com.ProjectManagerAPI.ProjectManager.mappers.Mapper;
import com.ProjectManagerAPI.ProjectManager.services.ProjectService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
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
    public List<ProjectDto> listProjects(){
        List<ProjectEntity> projects = projectService.findAll();
        return projects.stream().map(projectMapper::mapTo).collect(Collectors.toList());
    }

    @PostMapping(path = "/projects")
    public ResponseEntity<ProjectDto> createProject(@RequestBody ProjectDto projectDto){
        ProjectEntity projectEntity = projectMapper.mapFrom(projectDto);
        ProjectEntity savedProjectEntity = projectService.save(projectEntity);
        return new ResponseEntity<>(projectMapper.mapTo(savedProjectEntity), HttpStatus.CREATED);
    }
}
