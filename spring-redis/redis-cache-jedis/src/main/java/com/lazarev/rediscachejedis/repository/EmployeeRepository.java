package com.lazarev.rediscachejedis.repository;

import com.lazarev.rediscachejedis.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    @Query("select e from Employee e left join fetch e.projects where e.id = :id")
    Optional<Employee> findEmployeeAndProjectsById(Integer id);

    @Query("""
            select e from Employee e
            left join fetch e.grade
            left join fetch e.projects
            where e.id = :id
           """)
    Optional<Employee> findEmployeeById(Integer id);

    @Modifying
    @Query("delete from Employee e where e.id = :id")
    int deleteEmployeeById(Integer id);
}
