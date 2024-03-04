package com.ProjectManagerAPI.ProjectManager.mappers.implementations;

import com.ProjectManagerAPI.ProjectManager.domain.dto.WorkerDto;
import com.ProjectManagerAPI.ProjectManager.domain.entities.WorkerEntity;
import com.ProjectManagerAPI.ProjectManager.mappers.Mapper;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class WorkerMapperImpl implements Mapper<WorkerEntity, WorkerDto> {

    private final ModelMapper modelMapper;

    public WorkerMapperImpl(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public WorkerDto mapTo(WorkerEntity workerEntity) {
        return modelMapper.map(workerEntity, WorkerDto.class);
    }

    @Override
    public WorkerEntity mapFrom(WorkerDto workerDto) {
        return modelMapper.map(workerDto, WorkerEntity.class);
    }
}
