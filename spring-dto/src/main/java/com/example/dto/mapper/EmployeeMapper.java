package com.example.dto.mapper;

import com.example.dto.dto.DepartmentDto;
import com.example.dto.dto.EmployeeDto;
import com.example.dto.entity.Department;
import com.example.dto.entity.Employee;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface EmployeeMapper {
    Employee toEmployee(EmployeeDto employeeDto);
    EmployeeDto toDto(Employee employee);

    Department toDepartment(DepartmentDto departmentDto);
    DepartmentDto toDto(Department department);


    @BeforeMapping
    default void before(@MappingTarget Employee employee){
        System.out.println("before");
    }

    @AfterMapping
    default void after(@MappingTarget Employee employee){
        System.out.println("after");
    }
}
