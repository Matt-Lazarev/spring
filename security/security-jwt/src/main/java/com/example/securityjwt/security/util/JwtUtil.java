package com.example.securityjwt.security.util;

import com.example.securityjwt.security.config.JwtConfig;
import com.example.securityjwt.security.dto.UserInfo;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.*;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class JwtUtil {
    private final JwtConfig jwtConfig;
    private final SecretKey secretKey;

    public String generateAccessToken(String username, Collection<?> authorities) {
        String token =  Jwts.builder()
                .issuer("jwt-security-app")
                .subject(username)
                .claim("authorities", authorities)
                .issuedAt(new Date())
                .expiration(new Date(new Date().getTime() + jwtConfig.getTokenExpirationMs()))
                .signWith(secretKey, Jwts.SIG.HS256)
                .compact();
        return JwtConfig.TOKEN_PREFIX + token;
    }

    public UserInfo parseJwtToken(String authToken) {
        authToken = authToken.replace(JwtConfig.TOKEN_PREFIX, "");

        Claims claims = Jwts.parser()
                .verifyWith(secretKey)
                .build()
                .parseSignedClaims(authToken)
                .getPayload();

        return new UserInfo(claims.getSubject(), obtainAuthorities(claims));
    }

    @SuppressWarnings("unchecked")
    private Set<? extends GrantedAuthority> obtainAuthorities(Claims claims) {
        List<Map<String, String>> authorities =  claims.get("authorities", List.class);
        return authorities.stream()
                .map(x -> new SimpleGrantedAuthority(x.get("authority")))
                .collect(Collectors.toSet());
    }
}
