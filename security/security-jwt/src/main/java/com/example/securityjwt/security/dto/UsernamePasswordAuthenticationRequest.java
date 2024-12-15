package com.example.securityjwt.security.dto;

public record UsernamePasswordAuthenticationRequest
        (String username, String password) {
}
