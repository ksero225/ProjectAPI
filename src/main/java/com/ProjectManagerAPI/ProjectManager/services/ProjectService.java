package com.ProjectManagerAPI.ProjectManager.services;

import com.ProjectManagerAPI.ProjectManager.domain.entities.ProjectEntity;

import java.util.List;

public interface ProjectService {
    List<ProjectEntity> findAll();

    ProjectEntity save(ProjectEntity projectEntity);
}
