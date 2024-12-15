package com.lazarev.shortpolling.repository;

import com.lazarev.shortpolling.entity.Task;
import com.lazarev.shortpolling.entity.TaskStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Set;

public interface TaskRepository extends JpaRepository<Task, Integer> {

    List<Task> findAllByStatusIn(Set<TaskStatus> statuses);
}
