package com.ProjectManagerAPI.ProjectManager.services;

import com.ProjectManagerAPI.ProjectManager.domain.entities.WorkerEntity;

import java.util.List;
import java.util.Optional;

public interface WorkerService {
    List<WorkerEntity> findAll();

    WorkerEntity save(WorkerEntity workerEntity);

    Optional<WorkerEntity> findOne(Long workerId);
}
