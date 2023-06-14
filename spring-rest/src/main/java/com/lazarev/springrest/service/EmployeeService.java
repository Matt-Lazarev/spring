package com.lazarev.springrest.service;

import com.lazarev.springrest.dto.EmployeeAdditionRequest;
import com.lazarev.springrest.dto.EmployeeAdditionResponse;
import com.lazarev.springrest.dto.EmployeeDto;
import com.lazarev.springrest.entity.Department;
import com.lazarev.springrest.entity.Employee;
import com.lazarev.springrest.repository.EmployeeRepository;
import com.lazarev.springrest.service.mapper.EmployeeMapper;
import com.lazarev.springrest.service.mapper.MapstructEmployeeMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EmployeeService {
    private final EmployeeMapper employeeMapper;
    private final MapstructEmployeeMapper mapstructEmployeeMapper;
    private final EmployeeRepository employeeRepository;
    private final DepartmentService departmentService;

    @Transactional(readOnly = true)
    public List<EmployeeDto> getAllEmployees(){
        List<Employee> employees = employeeRepository.findAllEmployees();
        return mapstructEmployeeMapper.toEmployeeDtoList(employees);
    }

    @Transactional(readOnly = true)
    public EmployeeDto getEmployeeById(Integer id){
        Employee employee =  employeeRepository.findEmployeeById(id)
                .orElseThrow(()->new NoSuchElementException(
                        "Employee with id='%d not found".formatted(id)));
        return employeeMapper.toEmployeeDto(employee);
    }

    @Transactional
    public void saveEmployee(EmployeeDto employeeDto){
        Employee employee = employeeMapper.toEmployee(employeeDto);
        employeeRepository.save(employee);
    }

    @Transactional
    public void updateEmployee(Integer id, EmployeeDto employeeDto){
        Employee updatableEmployee = employeeRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException(
                        "Employee with id='%d not found".formatted(id)));

        mapstructEmployeeMapper.update(updatableEmployee, employeeDto);

        employeeRepository.save(updatableEmployee);
    }

    @Transactional
    public void deleteEmployee(Integer id){
        employeeRepository.deleteById(id);
    }

    @Transactional
    public EmployeeAdditionResponse addEmployeeToDepartment(EmployeeAdditionRequest request){
        Integer employeeId = request.getEmployeeId();
        Employee employee;
        if(employeeId == null){
            employee = new Employee();
            employee.setEmail(request.getEmail());
            employee.setName(request.getName());
        }
        else {
            Optional<Employee> employeeOptional = employeeRepository.findById(employeeId);
            if(employeeOptional.isEmpty()){
                return new EmployeeAdditionResponse(false, "Employee not found");
            }
            else {
                employee = employeeOptional.get();
            }
        }

        Integer departmentId = request.getDepartmentId();
        Department department;
        if(departmentId == null){
            department = new Department();
            department.setName(request.getDepartmentName());
        }
        else {
            department = departmentService.getDepartmentById(departmentId);
        }

        employee.setDepartment(department);
        employeeRepository.save(employee);
        return new EmployeeAdditionResponse(true, "Operation confirmed");
    }
}
