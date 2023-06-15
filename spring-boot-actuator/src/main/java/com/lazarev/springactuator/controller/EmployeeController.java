package com.lazarev.springactuator.controller;

import com.lazarev.springactuator.dto.EmployeeDto;
import com.lazarev.springactuator.entity.Employee;
import com.lazarev.springactuator.repository.EmployeeRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/employees")
public class EmployeeController {
    private final EmployeeRepository employeeRepository;

    @GetMapping
    public List<Employee> getAllEmployees(){
        return employeeRepository.findAll();
    }

    @GetMapping("/filter")
    public List<Employee> filterBooks(@ParameterObject Pageable pageable) {
        return employeeRepository.findAllEmployees(pageable);
    }


    @Operation(summary = "Get an employee by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the employee",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Employee.class)) }),
            @ApiResponse(responseCode = "400", description = "Employee ID not supplied"),
            @ApiResponse(responseCode = "404", description = "Employee not found") })
    @GetMapping("/{id}")
    public Employee getEmployeeById(@PathVariable Integer id){
        return employeeRepository.findById(id).get();
    }

    @PostMapping
    public void saveEmployee(@RequestBody EmployeeDto employeeDto){
        Employee employee = new Employee();
        employee.setName(employeeDto.name());
        employee.setEmail(employeeDto.email());

        employeeRepository.save(employee);
    }
}
