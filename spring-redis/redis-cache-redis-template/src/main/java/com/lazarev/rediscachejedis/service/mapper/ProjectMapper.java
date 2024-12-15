package com.lazarev.rediscachejedis.service.mapper;

import com.lazarev.rediscachejedis.dto.project.ProjectDto;
import com.lazarev.rediscachejedis.entity.Project;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

import static org.mapstruct.NullValuePropertyMappingStrategy.IGNORE;

@Mapper(componentModel = "spring")
public interface ProjectMapper {
    Project toEntity(ProjectDto dto);

    ProjectDto toDto(Project entity);

    List<ProjectDto> toDtoList(List<Project> entities);

    @Mapping(target = "id", ignore = true)
    void update(@MappingTarget Project entity, ProjectDto dto);

    @Mapping(target = "id", ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = IGNORE)
    void patch(@MappingTarget Project entity, ProjectDto dto);
}
