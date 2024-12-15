package com.lazarev.springrest.service;

import com.lazarev.springrest.dto.DepartmentDto;
import com.lazarev.springrest.dto.EmployeeDto;
import com.lazarev.springrest.entity.Department;
import com.lazarev.springrest.exception.ResourceNotFoundException;
import com.lazarev.springrest.repository.DepartmentRepository;
import com.lazarev.springrest.mapper.EmployeeDepartmentMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DepartmentService {
    private final DepartmentRepository departmentRepository;
    private final EmployeeDepartmentMapper employeeDepartmentMapper;
    private final EmployeeService employeeService;

    @Transactional(readOnly = true)
    public List<DepartmentDto> getDepartments() {
        List<Department> departments = departmentRepository.findAll();
        return employeeDepartmentMapper.toDepartmentDtos(departments);
    }

    @Transactional(readOnly = true)
    public DepartmentDto getDepartmentById(Integer id) {
        Department department = findDepartmentById(id);
        return employeeDepartmentMapper.toDepartmentDto(department);
    }

    @Transactional
    public void saveDepartment(DepartmentDto departmentDto) {
        Department department = employeeDepartmentMapper.toDepartment(departmentDto);
        departmentRepository.save(department);
    }

    @Transactional
    public void updateDepartment(Integer id, DepartmentDto departmentDto) {
        Department department = findDepartmentById(id);
        employeeDepartmentMapper.updateDepartment(department, departmentDto);
        departmentRepository.save(department);
    }

    @Transactional
    public void deleteDepartment(Integer id) {
        if (departmentRepository.deleteDepartmentById(id) == 0) {
            throw departmentNotFoundException(id);
        }
    }

    @Transactional
    public void createEmployeeInDepartment(Integer departmentId, EmployeeDto employeeDto) {
        employeeService.saveEmployeeInDepartment(employeeDto, departmentId);
    }

    @Transactional
    public void attachDepartmentToEmployee(Integer departmentId, Integer employeeId) {
        employeeService.attachDepartmentToEmployee(employeeId, departmentId);
    }

    @Transactional
    public void removeEmployeeFromDepartment(Integer departmentId, Integer employeeId) {
        employeeService.detachDepartmentFromEmployee(employeeId, departmentId);
    }

    private Department findDepartmentById(Integer id) {
        return departmentRepository.findById(id).orElseThrow(() -> departmentNotFoundException(id));
    }

    private ResourceNotFoundException departmentNotFoundException(Integer departmentId) {
        return new ResourceNotFoundException(
                "Department with id = '%d' not found".formatted(departmentId)
        );
    }
}
