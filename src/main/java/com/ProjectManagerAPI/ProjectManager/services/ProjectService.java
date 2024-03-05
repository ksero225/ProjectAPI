package com.ProjectManagerAPI.ProjectManager.services;

import com.ProjectManagerAPI.ProjectManager.domain.entities.ProjectEntity;

import java.util.List;
import java.util.Optional;

public interface ProjectService {
    List<ProjectEntity> findAll();

    ProjectEntity save(ProjectEntity projectEntity);

    Optional<ProjectEntity> findOne(Long projectId);
}
