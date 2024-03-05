package com.ProjectManagerAPI.ProjectManager.domain.dto;

import com.ProjectManagerAPI.ProjectManager.domain.entities.ProjectEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class WorkerDto {
    private Long workerId;
    private String workerName;
    private String workerSurname;


    private Set<ProjectEntity> projectsAssignedToThisWorker;
}
