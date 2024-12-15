package com.lazarev.rediscachejedis.repository;

import com.lazarev.rediscachejedis.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectRepository extends JpaRepository<Project, Integer> {
}
