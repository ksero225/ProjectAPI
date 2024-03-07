package com.ProjectManagerAPI.ProjectManager.services;

import com.ProjectManagerAPI.ProjectManager.domain.entities.ProjectEntity;

import java.util.List;
import java.util.Optional;

public interface ProjectService {

    ProjectEntity save(ProjectEntity projectEntity);

    Optional<ProjectEntity> findOne(Long projectId);

    List<ProjectEntity> findAll();

    ProjectEntity partialUpdate(Long projectId, ProjectEntity projectEntity);

    void deleteById(Long projectId);

    boolean doesProjectExists(Long projectId);
}
