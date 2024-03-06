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
import java.util.Optional;
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
    public Optional<WorkerEntity> findOne(Long workerId) {
        return workerRepository.findById(workerId);
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

    @Override
    public WorkerEntity partialUpdate(Long workerId, WorkerEntity workerEntity) {
        workerEntity.setWorkerId(workerId);

        return workerRepository.findById(workerId).map(existingWorker ->{
            Optional.ofNullable(workerEntity.getWorkerName()).ifPresent(existingWorker::setWorkerName);
            Optional.ofNullable(workerEntity.getWorkerSurname()).ifPresent(existingWorker::setWorkerSurname);
            Optional.ofNullable(workerEntity.getProjectsAssignedToThisWorker()).ifPresent(existingWorker::setProjectsAssignedToThisWorker);

            return workerRepository.save(existingWorker);
        }).orElseThrow(() -> new RuntimeException("Worker does not exists"));
    }

    @Override
    public void deleteById(Long workerId) {
        workerRepository.deleteById(workerId);
    }

    @Override
    public boolean doesWorkerExists(Long workerId) {
        return workerRepository.existsById(workerId);
    }
}
