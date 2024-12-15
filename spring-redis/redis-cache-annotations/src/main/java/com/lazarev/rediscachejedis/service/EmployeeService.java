package com.lazarev.rediscachejedis.service;

import com.lazarev.rediscachejedis.dto.employee.EmployeeDto;
import com.lazarev.rediscachejedis.dto.employee.EmployeeInfoDto;
import com.lazarev.rediscachejedis.dto.general.IdResponse;
import com.lazarev.rediscachejedis.entity.Employee;
import com.lazarev.rediscachejedis.entity.Grade;
import com.lazarev.rediscachejedis.entity.Project;
import com.lazarev.rediscachejedis.enums.Operation;
import com.lazarev.rediscachejedis.repository.EmployeeRepository;
import com.lazarev.rediscachejedis.service.mapper.EmployeeMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

import static com.lazarev.rediscachejedis.statics.StaticConstants.Fields.*;

@Service
@RequiredArgsConstructor
public class EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final EmployeeMapper employeeMapper;

    private final GradeService gradeService;
    private final ProjectService projectService;

    public List<EmployeeDto> getAllEmployees() {
        List<Employee> employees = employeeRepository.findAll();
        return employeeMapper.toDtoList(employees);
    }

    @Cacheable("employees")
    public EmployeeDto getEmployeeById(Integer id) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException(EMPLOYEE_NOT_FOUND.formatted(id)));

        return employeeMapper.toDto(employee);
    }

    @Cacheable("employee-infos")
    public EmployeeInfoDto getEmployeeInfoById(Integer id) {
        Employee employee = employeeRepository.findEmployeeById(id)
                .orElseThrow(() -> new NoSuchElementException(EMPLOYEE_NOT_FOUND.formatted(id)));
        return employeeMapper.toEmployeeInfo(employee);
    }

    @Transactional
    public IdResponse createEmployee(EmployeeDto employeeDto) {
        Employee employee = employeeMapper.toEntity(employeeDto);
        Employee savedEmployee = employeeRepository.save(employee);

        return new IdResponse(savedEmployee.getId(), Operation.SAVE);
    }

    @Transactional
    @CacheEvict(cacheNames = {"employees", "employee-infos"}, key = "#employeeId")
    public void addGradeToEmployee(Integer employeeId, Integer gradeId) {
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new NoSuchElementException(EMPLOYEE_NOT_FOUND.formatted(employeeId)));

        Grade grade = gradeService.getGradeReferenceById(gradeId);
        employee.setGrade(grade);
    }

    @Transactional
    @CacheEvict(cacheNames = {"employees", "employee-infos"}, key = "#employeeId")
    public void addProjectToEmployee(Integer employeeId, Integer projectId) {
        Employee employee = employeeRepository.findEmployeeAndProjectsById(employeeId)
                .orElseThrow(() -> new NoSuchElementException(EMPLOYEE_NOT_FOUND.formatted(employeeId)));

        Project project = projectService.getProjectReferenceById(projectId);
        employee.addProject(project);
    }

    @Transactional
    @CacheEvict(cacheNames = {"employees", "employee-infos"}, key = "#id")
    public IdResponse updateEmployee(Integer id, EmployeeDto employeeDto) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException(EMPLOYEE_NOT_FOUND.formatted(id)));

        employeeMapper.update(employee, employeeDto);

        return new IdResponse(id, Operation.UPDATE);
    }

    @Transactional
    @CacheEvict(cacheNames = {"employees", "employee-infos"}, key = "#id")
    public IdResponse patchEmployee(Integer id, EmployeeDto employeeDto) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException(EMPLOYEE_NOT_FOUND.formatted(id)));

        employeeMapper.patch(employee, employeeDto);

        return new IdResponse(id, Operation.PATCH);
    }

    @Transactional
    @CacheEvict(cacheNames = {"employees", "employee-infos"}, key = "#id")
    public IdResponse deleteEmployee(Integer id) {
        int rowsDeleted = employeeRepository.deleteEmployeeById(id);
        if (rowsDeleted != 1) {
            throw new NoSuchElementException(EMPLOYEE_NOT_FOUND.formatted(id));
        }

        return new IdResponse(id, Operation.DELETE);
    }
}
