package com.lazarev.springrest.service;

import com.lazarev.springrest.entity.Department;
import com.lazarev.springrest.repository.DepartmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class DepartmentService {
    private final DepartmentRepository departmentRepository;

    public Department getDepartmentById(Integer id){
        return departmentRepository.findById(id)
                .orElseThrow(()->new NoSuchElementException("Department not found"));
    }
}
