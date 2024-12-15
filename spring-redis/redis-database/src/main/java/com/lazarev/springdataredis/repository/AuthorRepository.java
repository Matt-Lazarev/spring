package com.lazarev.springdataredis.repository;

import com.lazarev.springdataredis.model.Author;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface AuthorRepository extends CrudRepository<Author, UUID> {
}
