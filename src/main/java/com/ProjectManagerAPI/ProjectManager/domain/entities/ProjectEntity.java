package com.ProjectManagerAPI.ProjectManager.domain.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "projects")
public class ProjectEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "project_id_seq")
    private Long projectId;
    private String projectTitle;
    private String projectDescription;
    private LocalDate projectDueDate;

    @ManyToMany(mappedBy = "projectsAssignedToThisWorker", fetch = FetchType.EAGER)
    private HashSet<WorkerEntity> workersAssignedToThisProject = new HashSet<>();
}
