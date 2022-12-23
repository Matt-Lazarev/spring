package com.lazarev.springcache.repository;

import com.lazarev.springcache.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ClientRepository extends JpaRepository<Client, Integer> {
    @Query("select c from Client c where c.email = :email")
    Client findClientByEmail(String email);

    @Query("select c from Client c where c.firstname = :firstname and c.lastname = :lastname")
    Client findClientByFirstnameAndLastname(String firstname, String lastname);
}
