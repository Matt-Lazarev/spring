package com.lazarev.springrest.repository;

import com.lazarev.springrest.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface DepartmentRepository extends JpaRepository<Department, Integer> {

    @Modifying
    @Query("delete from Department d where d.id = :id")
    int deleteDepartmentById(Integer id);
}
