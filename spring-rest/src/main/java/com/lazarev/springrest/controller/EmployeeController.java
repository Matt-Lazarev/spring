package com.lazarev.springrest.controller;

import com.lazarev.springrest.entity.Employee;
import com.lazarev.springrest.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/employees")
public class EmployeeController {
    private final EmployeeService employeeService;

    @GetMapping
    public List<Employee> getAllEmployees(){
        return employeeService.getAllEmployees();
    }

    @GetMapping("/{employeeId}")
    public Employee getEmployeeById(@PathVariable Integer employeeId){
        return employeeService.getEmployeeById(employeeId);
    }

    @PostMapping
    public void saveEmployee(@RequestBody Employee employee){
        employeeService.saveEmployee(employee);
    }

    @PutMapping("/{employeeId}")
    public void updateEmployee(@PathVariable Integer employeeId,
                               @RequestBody Employee employee){
        employeeService.updateEmployee(employeeId, employee);
    }

    @DeleteMapping("/{employeeId}")
    public void deleteEmployee(@PathVariable Integer employeeId){
        employeeService.deleteEmployee(employeeId);
    }

}
