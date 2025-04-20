package com.example.securityjwt.security.filter;

import com.example.securityjwt.security.config.JwtConfig;
import com.example.securityjwt.security.dto.UserInfo;
import com.example.securityjwt.security.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@RequiredArgsConstructor
public class JwtTokenVerifierFilter extends OncePerRequestFilter {
    private final JwtUtil jwtUtil;

    @Override
    @SneakyThrows
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)  {
        String token = obtainToken(request);
        if (token != null) {
            UserInfo user = jwtUtil.parseJwtToken(token);
            Authentication authentication = new UsernamePasswordAuthenticationToken(
                    user.username(), null, user.authorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);
            filterChain.doFilter(request, response);
        }
        else {
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
        }
    }

    private String obtainToken(HttpServletRequest request) {
        String authorizationHeader = request.getHeader(JwtConfig.HEADER);
        if (authorizationHeader == null || !authorizationHeader.startsWith(JwtConfig.TOKEN_PREFIX)) {
            return null;
        }

        return authorizationHeader.replace(JwtConfig.TOKEN_PREFIX, "");
    }
}
