package com.lazarev.securityoauth2authserver.repository;

import com.lazarev.securityoauth2authserver.model.entity.ApplicationUser;
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

