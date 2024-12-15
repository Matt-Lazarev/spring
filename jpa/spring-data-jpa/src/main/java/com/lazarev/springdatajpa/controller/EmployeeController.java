package com.lazarev.springdatajpa.controller;

import com.lazarev.springdatajpa.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/employees")
public class EmployeeController {
    private final EmployeeService employeeService;

    @GetMapping
    public String getAllEmployeesPage(Model model){
        model.addAttribute("employees", employeeService.getAllEmployees());
        return "employees";
    }

    @GetMapping("/{id}")
    public String getEmployeeByIdPage(@PathVariable Integer id, Model model){
        model.addAttribute("employees", List.of(employeeService.getEmployeeById(id)));
        return "employees";
    }
}
