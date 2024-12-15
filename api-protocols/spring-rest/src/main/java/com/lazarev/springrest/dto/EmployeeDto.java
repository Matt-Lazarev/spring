package com.lazarev.springrest.dto;

public record EmployeeDto(
        Integer id,
        String name,
        String email,
        DepartmentDto department) { }