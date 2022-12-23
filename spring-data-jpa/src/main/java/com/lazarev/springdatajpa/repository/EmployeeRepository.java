package com.lazarev.springdatajpa.repository;

import com.lazarev.springdatajpa.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

    Optional<Employee> findEmployeeByName(String name);

    @Modifying
    @Query("update Employee e set e.name = :name")
    int updateEmployeeName(String name);
}
