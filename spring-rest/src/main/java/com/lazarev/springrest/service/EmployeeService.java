package com.lazarev.springrest.service;

import com.lazarev.springrest.entity.Employee;
import com.lazarev.springrest.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class EmployeeService {
    private final EmployeeRepository employeeRepository;

    @Transactional(readOnly = true)
    public List<Employee> getAllEmployees(){
        return employeeRepository.findAllEmployees();
    }

    @Transactional(readOnly = true)
    public Employee getEmployeeById(Integer id){
        return employeeRepository.findEmployeeById(id)
                .orElseThrow(()->new NoSuchElementException(
                        "Employee with id='%d not found".formatted(id)));
    }

    @Transactional
    public void saveEmployee(Employee employee){
        employeeRepository.save(employee);
    }

    @Transactional
    public void updateEmployee(Integer id, Employee employee){
        Employee updatableEmployee = employeeRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException(
                        "Employee with id='%d not found".formatted(id)));

        updatableEmployee.setName(employee.getName());
        updatableEmployee.setEmail(employee.getEmail());
        updatableEmployee.setDepartment(employee.getDepartment());

        employeeRepository.save(updatableEmployee);
    }

    @Transactional
    public void deleteEmployee(Integer id){
        employeeRepository.deleteById(id);
    }
}
