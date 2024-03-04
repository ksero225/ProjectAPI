package com.ProjectManagerAPI.ProjectManager.services;

import com.ProjectManagerAPI.ProjectManager.domain.entities.WorkerEntity;

import java.util.List;

public interface WorkerService {
    List<WorkerEntity> findAll();

    WorkerEntity save(WorkerEntity workerEntity);
}
