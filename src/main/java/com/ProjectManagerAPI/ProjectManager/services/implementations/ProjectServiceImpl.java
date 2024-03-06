package com.ProjectManagerAPI.ProjectManager.services.implementations;

import com.ProjectManagerAPI.ProjectManager.domain.entities.ProjectEntity;
import com.ProjectManagerAPI.ProjectManager.repositories.ProjectRepository;
import com.ProjectManagerAPI.ProjectManager.services.ProjectService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class ProjectServiceImpl implements ProjectService {

    private final ProjectRepository projectRepository;

    public ProjectServiceImpl(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }


    @Override
    public ProjectEntity save(ProjectEntity projectEntity) {
        return projectRepository.save(projectEntity);
    }

    @Override
    public Optional<ProjectEntity> findOne(Long projectId) {
        return projectRepository.findById(projectId);
    }


//    @Override
//    public List<ProjectEntity> findAll() {
//        return StreamSupport.stream(
//                projectRepository
//                        .findAll()
//                        .spliterator(), false
//        ).collect(Collectors.toList());
//    }

    @Override
    public Page<ProjectEntity> findAll(Pageable pageable) {
        return projectRepository.findAll(pageable);
    }

    @Override
    public ProjectEntity partialUpdate(Long projectId, ProjectEntity projectEntity) {
        projectEntity.setProjectId(projectId);

        return projectRepository.findById(projectId).map(existingProject -> {
            Optional.ofNullable(projectEntity.getProjectTitle()).ifPresent(existingProject::setProjectTitle);
            Optional.ofNullable(projectEntity.getProjectDescription()).ifPresent(existingProject::setProjectDescription);
            Optional.ofNullable(projectEntity.getProjectDueDate()).ifPresent(existingProject::setProjectDueDate);
            Optional.ofNullable(projectEntity.getWorkersAssignedToThisProject()).ifPresent(existingProject::setWorkersAssignedToThisProject);

            return projectRepository.save(existingProject);
        }).orElseThrow(() -> new RuntimeException("Project does not exists"));
    }

    @Override
    public void deleteById(Long projectId) {
        projectRepository.deleteById(projectId);
    }

    @Override
    public boolean doesProjectExists(Long projectId) {
        return projectRepository.existsById(projectId);
    }
}
