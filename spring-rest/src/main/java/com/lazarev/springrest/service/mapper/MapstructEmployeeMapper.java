package com.lazarev.springrest.service.mapper;

import com.lazarev.springrest.dto.EmployeeDto;
import com.lazarev.springrest.entity.Employee;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MapstructEmployeeMapper {
    Employee toEmployee(EmployeeDto dto);

    EmployeeDto toEmployeeDto(Employee employee);

    List<EmployeeDto> toEmployeeDtoList(List<Employee> employees);

    void update(@MappingTarget Employee employee, EmployeeDto employeeDto);
}
