package com.lazarev.springsoapservercxf.service;

import com.lazarev.springsoapservercxf.model.StudentRequest;
import com.lazarev.springsoapservercxf.model.StudentResponse;
import jakarta.jws.WebMethod;
import jakarta.jws.WebService;

@WebService
public interface StudentWebService {

    @WebMethod
    String hello(String name);

    @WebMethod
    StudentResponse register(StudentRequest studentRequest);
}