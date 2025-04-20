package com.example.securityjwt.security.dto;

public record AuthRequest(
        String username,
        String password
) { }
