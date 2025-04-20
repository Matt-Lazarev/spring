package com.example.securityjwt.security.filter;

import com.example.securityjwt.security.config.JwtConfig;
import com.example.securityjwt.security.dto.AccessToken;
import com.example.securityjwt.security.dto.AuthRequest;
import com.example.securityjwt.security.util.JwtUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@RequiredArgsConstructor
public class CustomUsernamePasswordAuthFilter extends UsernamePasswordAuthenticationFilter {
    private static final ObjectMapper MAPPER = new ObjectMapper();

    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;

    @Override
    @SneakyThrows
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        if (!"POST".equals(request.getMethod())) {
            throw new AuthenticationServiceException
                    ("Authentication method not supported: " + request.getMethod());
        }

        AuthRequest authRequest = MAPPER.readValue(request.getInputStream(), AuthRequest.class);

        String username = authRequest.username();
        String password = authRequest.password();

        Authentication authentication = new UsernamePasswordAuthenticationToken(username, password);
        return authenticationManager.authenticate(authentication);
    }

    @Override
    @SneakyThrows
    protected void successfulAuthentication(
                             HttpServletRequest request, HttpServletResponse response,
                             FilterChain chain, Authentication authResult) {
        String accessToken = jwtUtil.generateAccessToken(authResult.getName(), authResult.getAuthorities());
        response.addHeader(JwtConfig.HEADER, accessToken);

        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        MAPPER.writeValue(response.getWriter(), new AccessToken(accessToken));
    }
}
