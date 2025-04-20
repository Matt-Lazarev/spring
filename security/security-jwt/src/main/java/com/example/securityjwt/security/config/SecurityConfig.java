package com.example.securityjwt.security.config;

import com.example.securityjwt.security.filter.CustomUsernamePasswordAuthFilter;
import com.example.securityjwt.security.filter.JwtTokenVerifierFilter;
import com.example.securityjwt.security.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.Http403ForbiddenEntryPoint;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;

@Configuration
@EnableMethodSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    private final UserDetailsService userDetailsService;
    private final JwtUtil jwtUtil;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
              .csrf(AbstractHttpConfigurer::disable)
              .sessionManagement(c -> c.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
              .authorizeHttpRequests(c -> c.anyRequest().authenticated())
              .addFilter(new CustomUsernamePasswordAuthFilter(authenticationManager(), jwtUtil))
              .addFilterAfter(new JwtTokenVerifierFilter(jwtUtil), CustomUsernamePasswordAuthFilter.class)
              .exceptionHandling(c -> c.authenticationEntryPoint(new Http403ForbiddenEntryPoint()))
              .httpBasic(AbstractHttpConfigurer::disable)
              .formLogin(AbstractHttpConfigurer::disable)
              .build();
    }

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService);
        provider.setPasswordEncoder(passwordEncoder());
        return provider;
    }

    @Bean
    public AuthenticationManager authenticationManager() {
        return new ProviderManager(daoAuthenticationProvider());
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder(10);
    }
}

