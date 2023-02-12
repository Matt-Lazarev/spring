package com.lazarev.securityoauth2authserver.model.security;

import com.lazarev.securityoauth2authserver.model.entity.Role;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;

@ToString
@RequiredArgsConstructor
public class SecurityRole implements GrantedAuthority {
    private final Role role;

    @Override
    public String getAuthority() {
        return role.getName().toUpperCase();
    }
}
