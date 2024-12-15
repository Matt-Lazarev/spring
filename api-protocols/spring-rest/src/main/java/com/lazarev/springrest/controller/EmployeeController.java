package com.lazarev.springrest.controller;

import com.lazarev.springrest.dto.DepartmentDto;
import com.lazarev.springrest.dto.EmployeeDto;
import com.lazarev.springrest.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/employees")
public class EmployeeController {
    private final EmployeeService employeeService;

    @GetMapping
    public ResponseEntity<List<EmployeeDto>> getAllEmployees() {
        List<EmployeeDto> employees = employeeService.getAllEmployees();
        return ResponseEntity.ok(employees);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmployeeDto> getEmployeeById(@PathVariable Integer id) {
        EmployeeDto employee = employeeService.findEmployeeById(id);
        return ResponseEntity.ok(employee);
    }

    @GetMapping("/{id}/department")
    public ResponseEntity<DepartmentDto> getEmployeeDepartment(@PathVariable Integer id) {
        DepartmentDto department = employeeService.getDepartmentByEmployeeId(id);
        return ResponseEntity.ok(department);
    }

    @PostMapping
    public ResponseEntity<Void> saveEmployee(@RequestBody EmployeeDto employeeDto) {
        employeeService.saveEmployee(employeeDto);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateEmployee(@PathVariable Integer id,
                                               @RequestBody EmployeeDto employeeDto) {
        employeeService.updateEmployee(id, employeeDto);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Void> patchEmployee(@PathVariable Integer id,
                                              @RequestBody EmployeeDto employeeDto) {
        employeeService.patchEmployee(id, employeeDto);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{employeeId}/department/{departmentId}")
    public ResponseEntity<Void> attachDepartmentToEmployee(@PathVariable Integer employeeId,
                                                           @PathVariable Integer departmentId) {
        employeeService.attachDepartmentToEmployee(employeeId, departmentId);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable Integer id) {
        employeeService.deleteEmployee(id);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}/department")
    public ResponseEntity<Void> detachDepartmentFromEmployee(@PathVariable Integer id) {
        employeeService.detachDepartmentFromEmployee(id);
        return ResponseEntity.ok().build();
    }
}
