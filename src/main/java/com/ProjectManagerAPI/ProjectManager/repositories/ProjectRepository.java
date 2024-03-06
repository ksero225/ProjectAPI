package com.ProjectManagerAPI.ProjectManager.repositories;

import com.ProjectManagerAPI.ProjectManager.domain.entities.ProjectEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectRepository extends CrudRepository<ProjectEntity, Long>, PagingAndSortingRepository<ProjectEntity, Long> {
}
