package com.lazarev.securityoauth2client.config;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(
        name = "hello-client",
        url = "http://localhost:8080/api/hello",
        configuration = OAuth2FeignConfig.class)
public interface HelloFeignClient {
    @GetMapping
    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN') and hasAuthority('SCOPE_openid')")
    String getHelloResponse();
}

