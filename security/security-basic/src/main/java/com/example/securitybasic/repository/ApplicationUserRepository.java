package com.example.securitybasic.repository;

import com.example.securitybasic.entity.ApplicationUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface ApplicationUserRepository
            extends JpaRepository<ApplicationUser, Integer> {

    @Query("select u from ApplicationUser u where u.login = :login")
    Optional<ApplicationUser> findApplicationUserByLogin(String login);
}

