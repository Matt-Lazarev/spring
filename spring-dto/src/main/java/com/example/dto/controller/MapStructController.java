package com.example.dto.controller;

import com.example.dto.dto.EmployeeDto;
import com.example.dto.entity.Employee;
import com.example.dto.mapper.EmployeeMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ms")
@RequiredArgsConstructor
public class MapStructController {
    private final EmployeeMapper employeeMapper;

    @PostMapping("/to-dto")
    public EmployeeDto toEmployeeDto(@RequestBody Employee employee){
        return employeeMapper.toDto(employee);
    }

    @PostMapping("/to-entity")
    public Employee toEmployee(@RequestBody EmployeeDto dto){
        return employeeMapper.toEmployee(dto);
    }
}
