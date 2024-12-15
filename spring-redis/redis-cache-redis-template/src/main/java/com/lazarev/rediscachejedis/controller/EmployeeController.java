package com.lazarev.rediscachejedis.controller;

import com.lazarev.rediscachejedis.dto.employee.EmployeeDto;
import com.lazarev.rediscachejedis.dto.employee.EmployeeInfoDto;
import com.lazarev.rediscachejedis.dto.general.IdResponse;
import com.lazarev.rediscachejedis.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employees")
@RequiredArgsConstructor
public class EmployeeController {
    private final EmployeeService employeeService;

    @GetMapping
    public ResponseEntity<List<EmployeeDto>> getAllEmployees() {
        List<EmployeeDto> employees = employeeService.getAllEmployees();
        return ResponseEntity.ok(employees);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmployeeDto> getEmployeeById(@PathVariable Integer id) {
        EmployeeDto employee = employeeService.getEmployeeById(id);
        return ResponseEntity.ok(employee);
    }

    @GetMapping("/{id}/info")
    public ResponseEntity<EmployeeInfoDto> getEmployeeInfoById(@PathVariable Integer id) {
        EmployeeInfoDto employee = employeeService.getEmployeeInfoById(id);
        return ResponseEntity.ok(employee);
    }

    @PostMapping
    public ResponseEntity<IdResponse> createEmployee(@RequestBody EmployeeDto employeeDto) {
        IdResponse idResponse = employeeService.createEmployee(employeeDto);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(idResponse);
    }

    @PutMapping("/{employeeId}/grade/{gradeId}")
    public ResponseEntity<?> addGradeToEmployee(@PathVariable Integer employeeId,
                                                @PathVariable Integer gradeId){
        employeeService.addGradeToEmployee(employeeId, gradeId);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{employeeId}/projects/{projectId}")
    public ResponseEntity<?> addProjectToEmployee(@PathVariable Integer employeeId,
                                                  @PathVariable Integer projectId){
        employeeService.addProjectToEmployee(employeeId, projectId);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<IdResponse> updateEmployee(@PathVariable Integer id,
                                                     @RequestBody EmployeeDto employeeDto) {
        IdResponse idResponse = employeeService.updateEmployee(id, employeeDto);
        return ResponseEntity.ok(idResponse);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<IdResponse> patchEmployee(@PathVariable Integer id,
                                                     @RequestBody EmployeeDto employeeDto) {
        IdResponse idResponse = employeeService.patchEmployee(id, employeeDto);
        return ResponseEntity.ok(idResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteEmployee(@PathVariable Integer id) {
        IdResponse idResponse = employeeService.deleteEmployee(id);
        return ResponseEntity.ok(idResponse);
    }
}
