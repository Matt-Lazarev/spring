package com.lazarev.securitykeycloakclient.controller;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/hello")
public class HelloController {

    @GetMapping
    public String getHelloResponse(Authentication authentication){
        return "Hello " + authentication.getName() + ", " + authentication.getAuthorities();
    }
}
