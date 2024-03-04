package com.ProjectManagerAPI.ProjectManager.mappers.implementations;

import com.ProjectManagerAPI.ProjectManager.domain.dto.ProjectDto;
import com.ProjectManagerAPI.ProjectManager.domain.entities.ProjectEntity;
import com.ProjectManagerAPI.ProjectManager.mappers.Mapper;
import org.modelmapper.ModelMapper;

public class ProjectMapperImpl implements Mapper<ProjectEntity, ProjectDto> {

    private final ModelMapper modelMapper;

    public ProjectMapperImpl(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public ProjectDto mapTo(ProjectEntity projectEntity) {
        return modelMapper.map(projectEntity, ProjectDto.class);
    }

    @Override
    public ProjectEntity mapFrom(ProjectDto projectDto) {
        return modelMapper.map(projectDto, ProjectEntity.class);
    }
}
