package com.ProjectManagerAPI.ProjectManager.controllers;


import com.ProjectManagerAPI.ProjectManager.domain.dto.WorkerDto;
import com.ProjectManagerAPI.ProjectManager.domain.entities.WorkerEntity;
import com.ProjectManagerAPI.ProjectManager.mappers.Mapper;
import com.ProjectManagerAPI.ProjectManager.services.WorkerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class WorkerController {

    private final WorkerService workerService;

    private final Mapper<WorkerEntity, WorkerDto> workerMapper;

    public WorkerController(WorkerService workerService, Mapper<WorkerEntity, WorkerDto> workerMapper) {
        this.workerService = workerService;
        this.workerMapper = workerMapper;
    }

    @GetMapping(path = "/workers")
    public List<WorkerDto> listWorkers(){
        List<WorkerEntity> workers = workerService.findAll();
        return workers.stream().map(workerMapper::mapTo).collect(Collectors.toList());
    }

    @PostMapping(path = "/workers")
    public ResponseEntity<WorkerDto> createWorker(@RequestBody WorkerDto workerDto){
        WorkerEntity workerEntity = workerMapper.mapFrom(workerDto);
        WorkerEntity savedWorkerEntity = workerService.save(workerEntity);
        return new ResponseEntity<>(workerMapper.mapTo(savedWorkerEntity), HttpStatus.CREATED);
    }
}
