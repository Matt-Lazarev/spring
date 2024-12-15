package com.lazarev.springrest.repository;

import com.lazarev.springrest.dto.EmployeeDto;
import com.lazarev.springrest.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

    @Query("select e from Employee e left join fetch e.department")
    List<Employee> findAllEmployees();

    @Query("""
              select e from Employee e
              left join fetch e.department
              where e.id = :id
           """)
    Optional<Employee> findEmployeeById(Integer id);

    @Modifying
    @Query("delete Employee e where e.id = :id")
    int deleteEmployeeById(Integer id);

    @Query(value = """
            insert into employees (name, email, department_id)
            select :#{#employee.name}, :#{#employee.email}, :departmentId
            where exists (select d.id from departments d where id = :departmentId)
            returning *
          """,
            nativeQuery = true)
    Optional<Employee> saveEmployeeInDepartment(EmployeeDto employee, Integer departmentId);

    @Query(value = """
              update employees e
              set department_id = :departmentId
              where e.id = :employeeId
              and exists (select d.id from departments d where d.id = :departmentId)
              returning *;
           """, nativeQuery = true)
    Optional<Employee> attachEmployeeToDepartment(Integer employeeId, Integer departmentId);

    @Query(value = """
              update employees e
              set department_id = null
              where e.id = :employeeId
              returning *;
           """, nativeQuery = true)
    Optional<Employee> detachEmployeeFromDepartment(Integer employeeId);

    @Query(value = """
              update employees e
              set department_id = null
              where e.id = :employeeId
              and exists (select d.id from departments d where d.id = :departmentId)
              returning *;
           """, nativeQuery = true)
    Optional<Employee> detachEmployeeFromDepartment(Integer employeeId, Integer departmentId);
}
