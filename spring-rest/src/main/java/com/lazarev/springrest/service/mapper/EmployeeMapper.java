package com.lazarev.springrest.service.mapper;

import com.lazarev.springrest.dto.EmployeeDto;
import com.lazarev.springrest.entity.Employee;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class EmployeeMapper {

    public List<EmployeeDto> toEmployeeDtoList(List<Employee> employees){
        return employees
                .stream()
                .map(this::toEmployeeDto)
                .toList();
    }

    public Employee toEmployee(EmployeeDto dto){
        Employee employee = new Employee();
        employee.setEmail(dto.getEmail());
        employee.setName(dto.getName());
        employee.setDepartment(dto.getDepartment());
        return employee;
    }

    public EmployeeDto toEmployeeDto(Employee employee){
        EmployeeDto dto = new EmployeeDto();
        dto.setEmail(employee.getEmail());
        dto.setName(employee.getName());
        dto.setDepartment(employee.getDepartment());
        return dto;
    }

    public void updateEmployee(Employee employee, EmployeeDto dto){
        employee.setName(dto.getName());
        employee.setEmail(dto.getEmail());
        employee.setDepartment(dto.getDepartment());
    }
}
