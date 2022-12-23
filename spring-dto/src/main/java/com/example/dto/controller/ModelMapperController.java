package com.example.dto.controller;

import com.example.dto.dto.CustomerDto;
import com.example.dto.entity.Customer;
import com.example.dto.entity.Employee;
import com.example.dto.dto.EmployeeDto;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class ModelMapperController {

    private final ModelMapper modelMapper;

    public Employee mapToEntity(EmployeeDto employeeDto){
        return modelMapper.map(employeeDto, Employee.class);
    }

    @PostMapping("/employee-dto")
    public EmployeeDto mapToDto(@RequestBody Employee employee){
        return modelMapper.map(employee, EmployeeDto.class);
    }

    @PostMapping("/customer-entity")
    public CustomerDto mapToDto(@RequestBody Customer customer){
        return modelMapper.map(customer, CustomerDto.class);
    }

    @PostMapping("/customer-dto")
    public Customer mapToDto(@RequestBody CustomerDto customerDto){
        return modelMapper.map(customerDto, Customer.class);
    }
}
