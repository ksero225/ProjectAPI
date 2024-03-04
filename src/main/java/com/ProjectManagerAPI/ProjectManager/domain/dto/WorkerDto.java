package com.ProjectManagerAPI.ProjectManager.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class WorkerDto {
    private Long workerId;
    private String workerName;
    private String workerSurname;
}
