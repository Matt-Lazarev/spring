package com.lazarev.springrest.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.CascadeType.*;
import static jakarta.persistence.GenerationType.IDENTITY;

@Getter
@Setter
@Entity
@Table(name = "departments")
public class Department {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Integer id;
    private String name;

    @OneToMany(cascade = {PERSIST, MERGE, DETACH, REFRESH}, mappedBy = "department")
    private List<Employee> employees = new ArrayList<>();

    public void addEmployee(Employee employee) {
        this.employees.add(employee);
        employee.setDepartment(this);
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
        employees.forEach(e -> e.setDepartment(this));
    }
}
