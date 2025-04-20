package com.example.securityjwt.security.dto;

import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public record UserInfo(
        String username,
        Collection<? extends GrantedAuthority> authorities
) { }
