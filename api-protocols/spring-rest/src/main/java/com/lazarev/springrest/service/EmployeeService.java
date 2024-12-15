package com.lazarev.springrest.service;

import com.lazarev.springrest.dto.DepartmentDto;
import com.lazarev.springrest.dto.EmployeeDto;
import com.lazarev.springrest.entity.Employee;
import com.lazarev.springrest.exception.ResourceNotFoundException;
import com.lazarev.springrest.repository.EmployeeRepository;
import com.lazarev.springrest.mapper.EmployeeDepartmentMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EmployeeService {
    private final EmployeeDepartmentMapper employeeDepartmentMapper;
    private final EmployeeRepository employeeRepository;

    @Transactional(readOnly = true)
    public List<EmployeeDto> getAllEmployees() {
        List<Employee> employees = employeeRepository.findAllEmployees();
        return employeeDepartmentMapper.toEmployeeDtos(employees);
    }

    @Transactional(readOnly = true)
    public EmployeeDto findEmployeeById(Integer id) {
        Employee employee = findEmployeeById(id, true);
        return employeeDepartmentMapper.toEmployeeDto(employee);
    }

    @Transactional(readOnly = true)
    public DepartmentDto getDepartmentByEmployeeId(Integer employeeId) {
        Employee employee = findEmployeeById(employeeId, true);

        return employeeDepartmentMapper.toDepartmentDto(employee.getDepartment());
    }

    @Transactional
    public void saveEmployee(EmployeeDto employeeDto) {
        Employee employee = employeeDepartmentMapper.toEmployee(employeeDto);
        employeeRepository.save(employee);
    }

    @Transactional
    public void  updateEmployee(Integer id, EmployeeDto employeeDto) {
        Employee updatableEmployee = findEmployeeById(id, false);
        employeeDepartmentMapper.updateEmployee(updatableEmployee, employeeDto);
        employeeRepository.save(updatableEmployee);
    }

    @Transactional
    public void patchEmployee(Integer id, EmployeeDto employeeDto) {
        Employee updatableEmployee = findEmployeeById(id, false);
        employeeDepartmentMapper.patchEmployee(updatableEmployee, employeeDto);
        employeeRepository.save(updatableEmployee);
    }

    @Transactional
    public void deleteEmployee(Integer id) {
        if (employeeRepository.deleteEmployeeById(id) == 0) {
            throw employeeNotFoundException(id);
        }
    }

    @Transactional
    public void saveEmployeeInDepartment(EmployeeDto employeeDto, Integer departmentId) {
        employeeRepository.saveEmployeeInDepartment(employeeDto, departmentId)
                .orElseThrow(() -> employeeOrDepartmentNotFoundException(employeeDto.id(), departmentId));
    }

    @Transactional
    public void attachDepartmentToEmployee(Integer employeeId, Integer departmentId) {
        employeeRepository.attachEmployeeToDepartment(employeeId, departmentId)
                .orElseThrow(() -> employeeOrDepartmentNotFoundException(employeeId, departmentId));
    }

    @Transactional
    public void detachDepartmentFromEmployee(Integer employeeId) {
        employeeRepository.detachEmployeeFromDepartment(employeeId)
                .orElseThrow(() -> employeeNotFoundException(employeeId));
    }

    @Transactional
    public void detachDepartmentFromEmployee(Integer employeeId, Integer departmentId) {
        employeeRepository.detachEmployeeFromDepartment(employeeId, departmentId)
                .orElseThrow(() -> employeeNotFoundException(employeeId));
    }

    private Employee findEmployeeById(Integer id, boolean fetchDepartment) {
        Optional<Employee> employee = fetchDepartment
                ? employeeRepository.findEmployeeById(id)
                : employeeRepository.findById(id);

        return employee.orElseThrow(() -> employeeNotFoundException(id));
    }

    private ResourceNotFoundException employeeNotFoundException(Integer employeeId) {
        return new ResourceNotFoundException("Employee with id = '%d' not found".formatted(employeeId));
    }

    private ResourceNotFoundException employeeOrDepartmentNotFoundException(Integer employeeId, Integer departmentId) {
        return new ResourceNotFoundException(
                "Employee with id = '%d' or department with id = '%d' not found".formatted(employeeId, departmentId)
        );
    }
}
