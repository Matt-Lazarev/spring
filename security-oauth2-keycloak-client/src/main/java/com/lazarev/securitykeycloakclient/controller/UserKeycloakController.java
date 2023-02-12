package com.lazarev.securitykeycloakclient.controller;

import com.lazarev.securitykeycloakclient.config.UsersKeycloakClient;
import lombok.RequiredArgsConstructor;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;


@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserKeycloakController {
    private final UsersKeycloakClient usersKeycloakClient;

    @GetMapping
    public List<UserRepresentation> getAllUsers(){
        return usersKeycloakClient.getAllUsers();
    }

    @GetMapping("/{username}")
    public UserRepresentation getUserByUsername(@PathVariable String username){
        List<UserRepresentation> users = usersKeycloakClient.getUserByUsername(username);
        if(users.size() == 1){
            return users.get(0);
        }
        throw new NoSuchElementException("User '%s' not found".formatted(username));
    }

    @GetMapping("/count")
    public Integer getUsersCount(){
        return usersKeycloakClient.getUsersCount();
    }
}
