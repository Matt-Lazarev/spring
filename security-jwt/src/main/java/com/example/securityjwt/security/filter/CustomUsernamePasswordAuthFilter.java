package com.example.securityjwt.security.filter;

import com.example.securityjwt.security.config.JwtConfig;
import com.example.securityjwt.security.dto.AccessToken;
import com.example.securityjwt.security.dto.UsernamePasswordAuthenticationRequest;
import com.example.securityjwt.security.utils.JwtUtil;
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

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RequiredArgsConstructor
public class CustomUsernamePasswordAuthFilter
        extends UsernamePasswordAuthenticationFilter {
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;

    @Override
    @SneakyThrows
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        if (!"POST".equals(request.getMethod())) {
            throw new AuthenticationServiceException
                    ("Authentication method not supported: " + request.getMethod());
        }

        var authRequest = new ObjectMapper()
                .readValue(request.getInputStream(), UsernamePasswordAuthenticationRequest.class);

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
        new ObjectMapper()
                .writerWithDefaultPrettyPrinter()
                .writeValue(response.getWriter(), new AccessToken(accessToken));
    }
}
