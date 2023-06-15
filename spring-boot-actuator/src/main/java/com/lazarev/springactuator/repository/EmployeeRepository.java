package com.lazarev.springactuator.repository;

import com.lazarev.springactuator.entity.Employee;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    @Query("from Employee")
    List<Employee> findAllEmployees(Pageable pageable);
}
