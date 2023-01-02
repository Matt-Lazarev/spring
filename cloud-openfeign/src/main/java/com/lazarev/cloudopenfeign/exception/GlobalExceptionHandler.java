package com.lazarev.cloudopenfeign.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.Instant;
import java.util.LinkedHashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({NoSuchPersonException.class, AccessDeniedException.class})
    public ResponseEntity<?> handleException(RuntimeException ex){
        Map<String, Object> errors = new LinkedHashMap<>();
        errors.put("timestamp", Instant.now());
        errors.put("status", HttpStatus.NOT_FOUND.value());
        errors.put("message", ex.getMessage());
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(errors);
    }
}
