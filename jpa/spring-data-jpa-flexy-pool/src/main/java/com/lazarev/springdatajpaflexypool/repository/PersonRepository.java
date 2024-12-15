package com.lazarev.springdatajpaflexypool.repository;

import com.lazarev.springdatajpaflexypool.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PersonRepository extends JpaRepository<Person, UUID> {
}
