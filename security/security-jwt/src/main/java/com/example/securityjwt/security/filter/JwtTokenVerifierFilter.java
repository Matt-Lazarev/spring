package com.example.securityjwt.security.filter;

import com.example.securityjwt.security.config.JwtConfig;
import com.example.securityjwt.security.dto.UserInfo;
import com.example.securityjwt.security.utils.JwtUtil;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RequiredArgsConstructor
public class JwtTokenVerifierFilter extends OncePerRequestFilter {
    private final JwtUtil jwtUtil;

    @Override
    @SneakyThrows
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response, FilterChain filterChain)  {
        String token = obtainToken(request);
        if (token != null) {
            UserInfo user = jwtUtil.parseJwtToken(token);
            Authentication authentication = new UsernamePasswordAuthenticationToken(
                    user.username(), null, user.authorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }

        filterChain.doFilter(request, response);
    }

    private String obtainToken(HttpServletRequest request) {
        String authorizationHeader = request.getHeader(JwtConfig.HEADER);
        if (authorizationHeader == null || !authorizationHeader.startsWith(JwtConfig.TOKEN_PREFIX)) {
            return null;
        }


        return authorizationHeader.replace(JwtConfig.TOKEN_PREFIX, "");
    }
}
