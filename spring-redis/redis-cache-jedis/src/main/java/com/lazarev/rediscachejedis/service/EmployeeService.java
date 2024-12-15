package com.lazarev.rediscachejedis.service;

import com.lazarev.rediscachejedis.dto.employee.EmployeeDto;
import com.lazarev.rediscachejedis.dto.employee.EmployeeInfoDto;
import com.lazarev.rediscachejedis.dto.general.IdResponse;
import com.lazarev.rediscachejedis.entity.Employee;
import com.lazarev.rediscachejedis.entity.Grade;
import com.lazarev.rediscachejedis.entity.Project;
import com.lazarev.rediscachejedis.enums.Operation;
import com.lazarev.rediscachejedis.repository.EmployeeRepository;
import com.lazarev.rediscachejedis.service.cache.EmployeeCachingService;
import com.lazarev.rediscachejedis.service.mapper.EmployeeMapper;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static com.lazarev.rediscachejedis.statics.StaticConstants.Fields.*;

@Service
@RequiredArgsConstructor
public class EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final EmployeeMapper employeeMapper;
    private final EmployeeCachingService employeeCachingService;

    private final GradeService gradeService;
    private final ProjectService projectService;

    public List<EmployeeDto> getAllEmployees() {
        List<Employee> employees = employeeRepository.findAll();
        return employeeMapper.toDtoList(employees);
    }

    public EmployeeDto getEmployeeById(Integer id) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException(EMPLOYEE_NOT_FOUND.formatted(id)));

        return employeeMapper.toDto(employee);
    }

    public EmployeeInfoDto getEmployeeInfoById(Integer id) {
        Employee employee = retrieveAndCacheEmployeeById(id);
        return employeeMapper.toEmployeeInfo(employee);
    }

    @Transactional
    public IdResponse createEmployee(EmployeeDto employeeDto) {
        Employee employee = employeeMapper.toEntity(employeeDto);
        Employee savedEmployee = employeeRepository.save(employee);

        return new IdResponse(savedEmployee.getId(), Operation.SAVE);
    }

    @Transactional
    public void addGradeToEmployee(Integer employeeId, Integer gradeId) {
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new NoSuchElementException(EMPLOYEE_NOT_FOUND.formatted(employeeId)));

        Grade grade = gradeService.getGradeReferenceById(gradeId);
        employee.setGrade(grade);

        employeeCachingService.evict(employeeId);
    }

    @Transactional
    public void addProjectToEmployee(Integer employeeId, Integer projectId) {
        Employee employee = employeeRepository.findEmployeeAndProjectsById(employeeId)
                .orElseThrow(() -> new NoSuchElementException(EMPLOYEE_NOT_FOUND.formatted(employeeId)));

        Project project = projectService.getProjectReferenceById(projectId);
        employee.addProject(project);

        employeeCachingService.evict(employeeId);
    }

    @Transactional
    public IdResponse updateEmployee(Integer id, EmployeeDto employeeDto) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException(EMPLOYEE_NOT_FOUND.formatted(id)));

        employeeMapper.update(employee, employeeDto);
        employeeCachingService.evict(id);

        return new IdResponse(id, Operation.UPDATE);
    }

    @Transactional
    public IdResponse patchEmployee(Integer id, EmployeeDto employeeDto) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException(EMPLOYEE_NOT_FOUND.formatted(id)));

        employeeMapper.patch(employee, employeeDto);
        employeeCachingService.evict(id);

        return new IdResponse(id, Operation.PATCH);
    }

    @Transactional
    public IdResponse deleteEmployee(Integer id) {
        int rowsDeleted = employeeRepository.deleteEmployeeById(id);
        if (rowsDeleted != 1) {
            throw new NoSuchElementException(EMPLOYEE_NOT_FOUND.formatted(id));
        }

        employeeCachingService.evict(id);
        return new IdResponse(id, Operation.DELETE);
    }

    @SneakyThrows
    private Employee retrieveAndCacheEmployeeById(Integer id){
        Optional<Employee> employeeOptional = employeeCachingService.get(id);
        if(employeeOptional.isPresent()){
            return employeeOptional.get();
        }

        Employee employee = employeeRepository.findEmployeeById(id)
                .orElseThrow(() -> new NoSuchElementException(EMPLOYEE_NOT_FOUND.formatted(id)));

        employeeCachingService.put(employee);
        return employee;
    }
}
