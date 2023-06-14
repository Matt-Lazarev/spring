package com.lazarev.springrest.controller;

import com.lazarev.springrest.dto.EmployeeAdditionRequest;
import com.lazarev.springrest.dto.EmployeeAdditionResponse;
import com.lazarev.springrest.dto.EmployeeDto;
import com.lazarev.springrest.entity.Employee;
import com.lazarev.springrest.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/employees")
public class EmployeeController {
    private final EmployeeService employeeService;

    @GetMapping
    public List<EmployeeDto> getAllEmployees(){
        return employeeService.getAllEmployees();
    }

    @GetMapping("/{employeeId}")
    public EmployeeDto getEmployeeById(@PathVariable Integer employeeId){
        return employeeService.getEmployeeById(employeeId);
    }

    @PostMapping
    public void saveEmployee(@RequestBody EmployeeDto employeeDto){
        employeeService.saveEmployee(employeeDto);
    }

    @PutMapping("/{employeeId}")
    public void updateEmployee(@PathVariable Integer employeeId,
                               @RequestBody EmployeeDto employeeDto){
        employeeService.updateEmployee(employeeId, employeeDto);
    }

    @DeleteMapping("/{employeeId}")
    public void deleteEmployee(@PathVariable Integer employeeId){
        employeeService.deleteEmployee(employeeId);
    }

    @PutMapping("/add-to-department")
    public EmployeeAdditionResponse addEmployeeToDepartment(@RequestBody EmployeeAdditionRequest request){
        return employeeService.addEmployeeToDepartment(request);
    }

}
