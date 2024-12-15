package com.example.securityjwt.security.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;


@Setter @Getter
@ConfigurationProperties(prefix = "jwt.config")
public class JwtConfig {
    public static final String HEADER = HttpHeaders.AUTHORIZATION;
    public static final String TOKEN_PREFIX = "Bearer ";

    private String secret;
    private int tokenExpirationMs;
}
