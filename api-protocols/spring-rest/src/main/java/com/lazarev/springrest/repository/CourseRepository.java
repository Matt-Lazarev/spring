package com.lazarev.springrest.repository;

import com.lazarev.springrest.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, Integer> {
}
