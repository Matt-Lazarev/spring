package com.lazarev.springrest.controller;

import com.lazarev.springrest.dto.DepartmentDto;
import com.lazarev.springrest.dto.EmployeeDto;
import com.lazarev.springrest.service.DepartmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/departments")
@RequiredArgsConstructor
public class DepartmentController {
    private final DepartmentService departmentService;

    @GetMapping
    public ResponseEntity<List<DepartmentDto>> getDepartments() {
        List<DepartmentDto> departments = departmentService.getDepartments();
        return ResponseEntity.ok(departments);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DepartmentDto> getDepartmentById(@PathVariable Integer id) {
        DepartmentDto department = departmentService.getDepartmentById(id);
        return ResponseEntity.ok(department);
    }

    @PostMapping
    public ResponseEntity<Void> saveDepartment(@RequestBody DepartmentDto departmentDto) {
        departmentService.saveDepartment(departmentDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateDepartment(@PathVariable Integer id,
                                              @RequestBody DepartmentDto departmentDto) {
         departmentService.updateDepartment(id, departmentDto);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDepartment(@PathVariable Integer id) {
        departmentService.deleteDepartment(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{departmentId}/employees")
    public ResponseEntity<Void> createEmployeeInDepartment(@PathVariable Integer departmentId,
                                                        @RequestBody EmployeeDto employeeDto) {
        departmentService.createEmployeeInDepartment(departmentId, employeeDto);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{departmentId}/employees/{employeeId}")
    public ResponseEntity<Void> attachDepartmentToEmployee(@PathVariable Integer departmentId,
                                                        @PathVariable Integer employeeId) {
        departmentService.attachDepartmentToEmployee(departmentId, employeeId);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{departmentId}/employees/{employeeId}")
    public ResponseEntity<Void> removeEmployeeFromDepartment(@PathVariable Integer departmentId,
                                                             @PathVariable Integer employeeId) {
        departmentService.removeEmployeeFromDepartment(departmentId, employeeId);
        return ResponseEntity.ok().build();
    }
}
