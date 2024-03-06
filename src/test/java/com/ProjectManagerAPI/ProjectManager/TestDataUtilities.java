package com.ProjectManagerAPI.ProjectManager;

import com.ProjectManagerAPI.ProjectManager.domain.dto.WorkerDto;
import com.ProjectManagerAPI.ProjectManager.domain.entities.ProjectEntity;
import com.ProjectManagerAPI.ProjectManager.domain.entities.WorkerEntity;

import java.time.LocalDate;
import java.util.HashSet;

public class TestDataUtilities {
    public static WorkerEntity createTestWorkerEntityA(){
        return WorkerEntity.builder()
                .workerId(1L)
                .workerName("Pawel")
                .workerSurname("Nowak")
                .projectsAssignedToThisWorker(
                        new HashSet<>()
                )
                .build();
    }


    public static WorkerDto createTestWorkerDtoA(){
        return WorkerDto.builder()
                .workerId(1L)
                .workerName("Pawel")
                .workerSurname("Nowak")
                .projectsAssignedToThisWorker(
                        new HashSet<>()
                )
                .build();
    }

    public static WorkerEntity createTestWorkerEntityB(){
        return WorkerEntity.builder()
                .workerId(2L)
                .workerName("Kamil")
                .workerSurname("Kowalski")
                .projectsAssignedToThisWorker(
                        new HashSet<>()
                )
                .build();
    }
    public static WorkerEntity createTestWorkerEntityC(){
        return WorkerEntity.builder()
                .workerId(3L)
                .workerName("Adam")
                .workerSurname("Malysz")
                .projectsAssignedToThisWorker(
                        new HashSet<>()
                )
                .build();
    }

    public static ProjectEntity createProjectEntityA(){
        LocalDate date = LocalDate.of(2024, 1, 1); //THIS ONE IS OUTDATED
        return ProjectEntity.builder()
                .projectId(1L)
                .projectTitle("Taskify")
                .projectDescription("Task Management Application")
                .projectDueDate(date)
                .workersAssignedToThisProject(
                        new HashSet<>()
                )
                .build();
    }
    public static ProjectEntity createProjectEntityB(){
        LocalDate date = LocalDate.of(2025, 2, 2);
        return ProjectEntity.builder()
                .projectId(2L)
                .projectTitle("FitBuddy")
                .projectDescription("Physical Activity Monitoring App")
                .projectDueDate(date)
                .workersAssignedToThisProject(
                        new HashSet<>()
                )
                .build();
    }
    public static ProjectEntity createProjectEntityC(){
        LocalDate date = LocalDate.of(2025, 3, 3);
        return ProjectEntity.builder()
                .projectId(3L)
                .projectTitle("Bookshelf")
                .projectDescription("Book Collection Management App")
                .projectDueDate(date)
                .workersAssignedToThisProject(
                        new HashSet<>()
                )
                .build();
    }

}
