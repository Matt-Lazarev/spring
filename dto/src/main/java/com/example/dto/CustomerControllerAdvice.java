package com.example.dto;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice(annotations = CustomExceptionHandler.class)
public class CustomerControllerAdvice {

    @ExceptionHandler
    public ResponseEntity<?> handleAllExceptions(){
        return ResponseEntity.badRequest().build();
    }
}

