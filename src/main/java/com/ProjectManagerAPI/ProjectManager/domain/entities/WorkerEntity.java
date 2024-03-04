package com.ProjectManagerAPI.ProjectManager.domain.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "workers")
public class WorkerEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "worker_id_seq")
    private Long workerId;
    private String workerName;
    private String workerSurname;

    @ManyToMany(cascade = {CascadeType.ALL}, fetch = FetchType.EAGER)
    @JoinTable(
            name = "Worker_Project",
            joinColumns = {@JoinColumn(name = "worker_id_seq")},
            inverseJoinColumns = {@JoinColumn(name = "project_id_seq")}
    )
    private Set<ProjectEntity> projectsAssignedToThisWorker = new HashSet<>();
}
