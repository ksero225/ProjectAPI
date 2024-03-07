package com.ProjectManagerAPI.ProjectManager.services;

import com.ProjectManagerAPI.ProjectManager.domain.entities.WorkerEntity;

import java.util.List;
import java.util.Optional;

public interface WorkerService {

    WorkerEntity save(WorkerEntity workerEntity);
    List<WorkerEntity> findAll();

    Optional<WorkerEntity> findOne(Long workerId);

    WorkerEntity partialUpdate(Long workerId, WorkerEntity workerEntity);

    void deleteById(Long workerId);

    boolean doesWorkerExists(Long workerId);
}
