package com.example.springvalidation.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.*;

@Getter
@Setter
public class CustomerDto {
    @Size(min = 2, max = 15, message = "Firstname should be from 2 to 15")
    private String firstname;

    @Size(min = 2, max = 15, message = "Lastname should be from 2 to 15")
    private String lastname;

    @Email(message = "Email is not correct")
    private String email;

    @Size(min = 3, max = 10, message = "Password should be from 3 to 10")
    private String password;

    @Min(value = 0, message = "Age should not be negative")
    @Max(value = 100, message = "Age should not be more than 100")
    private Integer age;
}
