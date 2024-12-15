package com.lazarev.rediscachejedis.dto.employee;

public record EmployeeDto (
        Integer id,
        String firstname,
        String lastname,
        Integer salary
) { }
