package com.lazarev.springcoreadvanced.service;

import com.lazarev.springcoreadvanced.annotation.Profiling;
import org.springframework.stereotype.Component;

@Profiling
@Component
public class CalculatorImpl implements Calculator{
    @Override
    public int add(int n1, int n2){
        return n1 + n2;
    }

    @Override
    public int mul(int n1, int n2){
        return n1 * n2;
    }
}
