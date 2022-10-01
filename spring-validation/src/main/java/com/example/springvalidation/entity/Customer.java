package com.example.springvalidation.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.*;

@Getter
@Setter
@Entity
@Table(name = "customers")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Firstname cannot be null")
    @NotBlank(message = "Firstname cannot be empty or blank")
    private String firstname;

    @NotNull(message = "Lastname cannot be null")
    @NotBlank(message = "Lastname cannot be empty or blank")
    private String lastname;

    @Email(message = "Wrong email format")
    private String email;

    @NotBlank(message = "Password cannot be empty or blank")
    @Size(min = 3, max = 20, message = "Password should be from 3 to 20 symbols")
    private String password;

    @Min(value = 0, message = "Age should be at least 0")
    @Max(value = 100, message = "Age should be at most 100")
    private Integer age;
}
