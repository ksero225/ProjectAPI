package com.ProjectManagerAPI.ProjectManager.services.implementations;

import com.ProjectManagerAPI.ProjectManager.domain.entities.ProjectEntity;
import com.ProjectManagerAPI.ProjectManager.domain.entities.WorkerEntity;
import com.ProjectManagerAPI.ProjectManager.repositories.ProjectRepository;
import com.ProjectManagerAPI.ProjectManager.repositories.WorkerRepository;
import com.ProjectManagerAPI.ProjectManager.services.WorkerService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class WorkerServiceImpl implements WorkerService {

    private final WorkerRepository workerRepository;
    private final ProjectRepository projectRepository;

    public WorkerServiceImpl(WorkerRepository workerRepository, ProjectRepository projectRepository) {
        this.workerRepository = workerRepository;
        this.projectRepository = projectRepository;
    }

    @Override
    public WorkerEntity save(WorkerEntity workerEntity) {
        HashSet<ProjectEntity> projectsWorkerIsAssignedTo = new HashSet<>(workerEntity.getProjectsAssignedToThisWorker());
        if(projectsWorkerIsAssignedTo.isEmpty()){
            return workerRepository.save(workerEntity);
        }

        for(ProjectEntity project: projectsWorkerIsAssignedTo){
            if(!projectRepository.existsById(project.getProjectId())){
                throw new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Project not found"
                );
            }
        }

        return workerRepository.save(workerEntity);
    }

    @Override
    public List<WorkerEntity> findAll() {
        return StreamSupport
                .stream(
                        workerRepository
                                .findAll()
                                .spliterator(), false
                ).collect(Collectors.toList());
    }

}
