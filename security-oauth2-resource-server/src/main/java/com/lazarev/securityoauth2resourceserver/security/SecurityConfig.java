package com.lazarev.securityoauth2resourceserver.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.oauth2.server.resource.OAuth2ResourceServerConfigurer;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.web.SecurityFilterChain;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests()
                    .anyRequest()
                    .authenticated()
                    .and()
                .oauth2ResourceServer()
                    .jwt()
                    .jwtAuthenticationConverter(jwtAuthenticationConverter());
        return http.build();
    }

    @Bean
    public JwtAuthenticationConverter jwtAuthenticationConverter() {
        JwtAuthenticationConverter jwtAuthenticationConverter = new JwtAuthenticationConverter();
        jwtAuthenticationConverter.setJwtGrantedAuthoritiesConverter(jwtConverter());
        return jwtAuthenticationConverter;
    }

    @SuppressWarnings({"unchecked"})
    private Converter<Jwt, Collection<GrantedAuthority>> jwtConverter() {
        return jwt -> {
            List<String> roles = (List<String>) jwt.getClaims().get("roles");
            List<String> scopes = (List<String>) jwt.getClaims().get("scope");

            roles = roles == null
                    ? new ArrayList<>()
                    : roles.stream().map(r -> "ROLE_" + r).toList();

            scopes = scopes == null
                    ? new ArrayList<>()
                    : scopes.stream().map(s -> "SCOPE_" + s).toList();

            return Stream.concat(roles.stream(), scopes.stream())
                    .map(role -> (GrantedAuthority) new SimpleGrantedAuthority(role))
                    .toList();
        };
    }
}
