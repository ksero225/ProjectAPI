package com.ProjectManagerAPI.ProjectManager.repositories;

import com.ProjectManagerAPI.ProjectManager.domain.entities.WorkerEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WorkerRepository extends CrudRepository<WorkerEntity, Long> {
}
