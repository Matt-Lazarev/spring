package com.example.securitycustomauth.security.dto;

import org.springframework.http.HttpStatus;

public record ErrorResponse (
        HttpStatus httpStatus,
        String error
) { }
