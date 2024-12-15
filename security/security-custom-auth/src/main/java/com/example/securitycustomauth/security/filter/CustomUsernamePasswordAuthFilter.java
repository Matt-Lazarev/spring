package com.example.securitycustomauth.security.filter;

import com.example.securitycustomauth.security.dto.ErrorResponse;
import com.example.securitycustomauth.security.dto.UsernamePasswordAuthenticationRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@RequiredArgsConstructor
public class CustomUsernamePasswordAuthFilter extends UsernamePasswordAuthenticationFilter {
    private static final ObjectMapper MAPPER = new ObjectMapper();

    private final AuthenticationManager authenticationManager;

    @Override
    @SneakyThrows
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        if (!"POST".equals(request.getMethod())) {
            throw new AuthenticationServiceException("Authentication method not supported: " + request.getMethod());
        }

        var authRequest = new ObjectMapper()
                .readValue(request.getInputStream(), UsernamePasswordAuthenticationRequest.class);

        Authentication authentication = new UsernamePasswordAuthenticationToken(authRequest.username(), authRequest.password());
        return authenticationManager.authenticate(authentication);
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest req, HttpServletResponse res,
                                            FilterChain chain, Authentication authResult) {
        SecurityContext context = SecurityContextHolder.getContext();
        context.setAuthentication(authResult);
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest req, HttpServletResponse res,
                                              AuthenticationException ex) throws IOException {
        res.setContentType(MediaType.APPLICATION_JSON_VALUE);
        res.setStatus(HttpStatus.UNAUTHORIZED.value());
        MAPPER.writerWithDefaultPrettyPrinter()
                .writeValue(res.getWriter(), new ErrorResponse(HttpStatus.UNAUTHORIZED, ex.getMessage()));
    }
}
