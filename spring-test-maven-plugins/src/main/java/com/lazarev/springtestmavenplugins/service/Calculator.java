package com.lazarev.springtestmavenplugins.service;

import org.springframework.stereotype.Service;

@Service
public class Calculator {

    public int add(int n1, int n2){
        return n1 + n2;
    }

    public int divide(int n1, int n2){
        if(n2 == 0){
            throw new ArithmeticException("Division by zero");
        }
        return n1 / n2;
    }
}
