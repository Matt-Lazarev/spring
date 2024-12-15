package com.lazarev.securitykeycloakclient.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class ClientSecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        http
                .authorizeHttpRequests()
                    .mvcMatchers("/api/users/**")
                    .permitAll()
                    .anyRequest()
                    .authenticated()
                    .and()
                .oauth2Login();
        return http.build();
    }
}
