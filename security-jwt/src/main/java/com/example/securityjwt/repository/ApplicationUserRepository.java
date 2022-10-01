package com.example.securityjwt.repository;

import com.example.securityjwt.entity.ApplicationUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface ApplicationUserRepository
            extends JpaRepository<ApplicationUser, Integer> {

    @Query("""
    select u from ApplicationUser u
    inner join fetch u.roles
    where u.login = :login
    """)
    Optional<ApplicationUser> findApplicationUserByLogin(String login);
}

