package com.lazarev.springtestmavenplugins.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CalculatorTest {
    private Calculator calculator;

    @BeforeEach
    void init(){
        calculator = new Calculator();
    }

    @Test
    void addTest(){
        int n1 = 10;
        int n2 = 20;

        assertEquals(30, calculator.add(n1, n2));
    }

    @Test
    void divide_secondParameterIsZero_arithmeticException(){
        int n1 = 10;
        int n2 = 0;

        Exception ex = assertThrows(ArithmeticException.class, () -> calculator.divide(n1, n2));
        assertEquals("Division by zero", ex.getMessage());
    }

    @Test
    void divide_secondParameterIsNotZero_validTest(){
        int n1 = 10;
        int n2 = 2;

        assertEquals(5, calculator.divide(n1, n2));
    }
}