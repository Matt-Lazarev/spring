package com.lazarev.securitykeycloakclient.config;

import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(
        name = "users-keycloak-client",
        url = "http://localhost:8181/admin/realms/oauth-demo-realm",
        configuration = OAuth2FeignConfig.class)
public interface UsersKeycloakClient {

    @GetMapping("/users")
    List<UserRepresentation> getAllUsers();

    @GetMapping("/users")
    List<UserRepresentation> getUserByUsername(@RequestParam("username") String username);

    @GetMapping("/users/count")
    Integer getUsersCount();
}
