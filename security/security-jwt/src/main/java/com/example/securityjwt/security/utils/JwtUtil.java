package com.example.securityjwt.security.utils;

import com.example.securityjwt.security.config.JwtConfig;
import com.example.securityjwt.security.dto.UserInfo;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
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

    public String generateAccessToken(String username, Collection<? extends GrantedAuthority> authorities) {
        String token =  Jwts.builder()
                .setIssuer("jwt-security-app")
                .setSubject(username)
                .claim("authorities", authorities)
                .setIssuedAt(new Date())
                .setExpiration(new Date(new Date().getTime() + jwtConfig.getTokenExpirationMs()))
                .signWith(secretKey, SignatureAlgorithm.HS256)
                .compact();
        return JwtConfig.TOKEN_PREFIX + token;
    }

    public UserInfo parseJwtToken(String authToken) {
        authToken = authToken.replace(JwtConfig.TOKEN_PREFIX, "");

        Claims claims = Jwts.parserBuilder()
                .setSigningKey(secretKey)
                .build()
                .parseClaimsJws(authToken)
                .getBody();

        return new UserInfo((String)claims.get("username"),  obtainAuthorities(claims));
    }

    @SuppressWarnings("unchecked")
    private Set<? extends GrantedAuthority> obtainAuthorities(Claims claims) {
        List<Map<String, String>> authorities =  claims.get("authorities", List.class);
        return authorities
                .stream()
                .map(x -> new SimpleGrantedAuthority(x.get("authority")))
                .collect(Collectors.toSet());
    }
}
