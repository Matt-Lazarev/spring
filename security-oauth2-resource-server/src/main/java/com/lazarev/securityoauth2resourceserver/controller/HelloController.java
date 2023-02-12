package com.lazarev.securityoauth2resourceserver.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("/api/hello")
public class HelloController {

    @GetMapping
    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN') and hasAuthority('SCOPE_openid')")
    public String getHelloResponse(Authentication authentication){
        return "Hello, " + authentication.getName() + " " + authentication.getAuthorities();
    }
}
