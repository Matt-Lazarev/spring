package com.lazarev.springrest.repository;

import com.lazarev.springrest.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
}
