package com.ProjectManagerAPI.ProjectManager.services;

import com.ProjectManagerAPI.ProjectManager.domain.entities.WorkerEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface WorkerService {

    WorkerEntity save(WorkerEntity workerEntity);
    //List<WorkerEntity> findAll();

    Page<WorkerEntity> findAll(Pageable pageable);

    Optional<WorkerEntity> findOne(Long workerId);

    WorkerEntity partialUpdate(Long workerId, WorkerEntity workerEntity);

    void deleteById(Long workerId);

    boolean doesWorkerExists(Long workerId);
}
