package com.lazarev.springsoapservercxf.service;

import com.lazarev.springsoapservercxf.model.StudentRequest;
import com.lazarev.springsoapservercxf.model.StudentResponse;
import jakarta.jws.WebService;
import org.springframework.stereotype.Service;

@Service
@WebService(endpointInterface = "com.lazarev.springsoapservercxf.service.StudentWebService")
public class StudentWebServiceImpl implements StudentWebService {
    public String hello(String name) {
        return "Hello, " + name;
    }

    public StudentResponse register(StudentRequest student) {
        return new StudentResponse("Student %s has been registered".formatted(student.getName()));
    }
}