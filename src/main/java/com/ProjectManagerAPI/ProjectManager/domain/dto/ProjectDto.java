package com.ProjectManagerAPI.ProjectManager.domain.dto;

import com.ProjectManagerAPI.ProjectManager.domain.entities.WorkerEntity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProjectDto {
    private Long projectId;
    private String projectTitle;
    private String projectDescription;
    private LocalDate projectDueDate;
    private Set<WorkerEntity> workersAssignedToThisProject;
}
