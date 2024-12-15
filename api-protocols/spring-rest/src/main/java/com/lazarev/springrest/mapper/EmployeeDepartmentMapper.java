package com.lazarev.springrest.mapper;

import com.lazarev.springrest.dto.DepartmentDto;
import com.lazarev.springrest.dto.EmployeeDto;
import com.lazarev.springrest.entity.Department;
import com.lazarev.springrest.entity.Employee;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring")
public interface EmployeeDepartmentMapper {
    Employee toEmployee(EmployeeDto dto);

    EmployeeDto toEmployeeDto(Employee employee);

    List<EmployeeDto> toEmployeeDtos(List<Employee> employees);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "department", ignore = true)
    void updateEmployee(@MappingTarget Employee employee, EmployeeDto dto);

    @Mapping(target = "id", ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void patchEmployee(@MappingTarget Employee employee, EmployeeDto dto);

    Department toDepartment(DepartmentDto dto);

    DepartmentDto toDepartmentDto(Department department);

    List<DepartmentDto> toDepartmentDtos(List<Department> department);

    @Mapping(target = "id", ignore = true)
    void updateDepartment(@MappingTarget Department department, DepartmentDto dto);
}
