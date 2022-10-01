package com.example.securitybasic.config.security;

import com.example.securitybasic.entity.ApplicationUser;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

import java.util.stream.Stream;

@RequiredArgsConstructor
public class SecurityUser implements UserDetails {
    private final ApplicationUser applicationUser;

    @Override
    public String getUsername() {
        return applicationUser.getLogin();
    }

    @Override
    public String getPassword() {
        return applicationUser.getPassword();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        String userRole = "ROLE_" + applicationUser.getRole();
        return Stream.of(userRole).map(SimpleGrantedAuthority::new).toList();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
