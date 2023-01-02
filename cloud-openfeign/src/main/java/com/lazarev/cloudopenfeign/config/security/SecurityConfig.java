package com.lazarev.cloudopenfeign.config.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Value("${person-service.username")
    private String username;

    @Value("${person-service.password")
    private String password;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .authorizeHttpRequests()
                    .mvcMatchers("/api/feign/people/**").permitAll()
                    .anyRequest()
                    .authenticated()
                    .and()
                .httpBasic()
                    .and()
                .build();
    }

    @Bean
    public UserDetailsService inMemoryUserDetailsService(){
        InMemoryUserDetailsManager uds = new InMemoryUserDetailsManager();
        UserDetails user = User
                .withUsername(username)
                .password(passwordEncoder().encode(password))
                .roles("USER")
                .build();
        uds.createUser(user);
        return uds;
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
