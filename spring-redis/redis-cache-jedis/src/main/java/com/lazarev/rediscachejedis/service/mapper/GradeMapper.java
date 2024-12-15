package com.lazarev.rediscachejedis.service.mapper;

import com.lazarev.rediscachejedis.dto.grade.GradeDto;
import com.lazarev.rediscachejedis.entity.Grade;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

import static org.mapstruct.NullValuePropertyMappingStrategy.IGNORE;

@Mapper(componentModel = "spring")
public interface GradeMapper {
    Grade toEntity(GradeDto dto);

    GradeDto toDto(Grade entity);

    List<GradeDto> toDtoList(List<Grade> entities);

    @Mapping(target = "id", ignore = true)
    void update(@MappingTarget Grade entity, GradeDto dto);

    @Mapping(target = "id", ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = IGNORE)
    void patch(@MappingTarget Grade entity, GradeDto dto);
}
