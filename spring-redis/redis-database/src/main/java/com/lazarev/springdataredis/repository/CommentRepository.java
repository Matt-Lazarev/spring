package com.lazarev.springdataredis.repository;

import com.lazarev.springdataredis.model.Comment;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface CommentRepository extends CrudRepository<Comment, UUID> {
}
