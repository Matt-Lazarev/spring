package com.lazarev.springactuator.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record EmployeeDto (
        Integer id,

        @Size(min = 2, max = 30)
        @Pattern(regexp = "[A-Z].*", message = "name should start with capital letter")
        String name,

        @Size(min = 2, max = 30)
        @Email(message = "field should match email format")
        String email) { }
