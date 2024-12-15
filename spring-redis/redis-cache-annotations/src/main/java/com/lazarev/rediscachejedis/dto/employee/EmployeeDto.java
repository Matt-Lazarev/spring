package com.lazarev.rediscachejedis.dto.employee;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public record EmployeeDto (
        Integer id,
        String firstname,
        String lastname,
        Integer salary
) implements Serializable { }
