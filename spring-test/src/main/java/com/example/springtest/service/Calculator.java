package com.example.springtest.service;

import org.springframework.stereotype.Service;

@Service
public class Calculator {
    public double add(double n1, double n2){
        return n1 + n2;
    }

    public double subtract(double n1, double n2){
        return n1 - n2;
    }

    public double divide(double n1, double n2){
        if(n2 == 0){
            throw new ArithmeticException("Parameter 'n2' cannot be zero");
        }
        return n1 / n2;
    }

    public boolean isPositive(double n){
        return n > 0;
    }
}
