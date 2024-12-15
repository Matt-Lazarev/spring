package com.lazarev.shortpolling.repository;

import com.lazarev.shortpolling.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Integer> {
}
