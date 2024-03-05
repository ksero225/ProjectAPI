package com.ProjectManagerAPI.ProjectManager.domain.dto;

import com.ProjectManagerAPI.ProjectManager.domain.entities.WorkerEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProjectDto {
    private Long projectId;
    private String projectTitle;
    private String projectDescription;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate projectDueDate;

    private Set<WorkerEntity> workersAssignedToThisProject;
}
