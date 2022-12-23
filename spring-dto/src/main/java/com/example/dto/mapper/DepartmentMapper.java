package com.example.dto.mapper;

import com.example.dto.dto.DepartmentDto;
import com.example.dto.entity.Department;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface DepartmentMapper {

//    @Mapping(target = "type", source = "departmentDto.departmentType")
//    @Mapping(target="employees", expression = "java(Integer.parseInt(departmentDto.employees()))")
//    Department toDepartment(DepartmentDto departmentDto);
//
//    @Mapping(target = "departmentType", source = "department.type")
//    @Mapping(target="employees", expression = "java(Integer.toString(department.getEmployees()))")
//    DepartmentDto toDepartmentDto(Department department);
}
