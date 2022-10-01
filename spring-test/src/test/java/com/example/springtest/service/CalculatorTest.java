package com.example.springtest.service;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class CalculatorTest {

    @Autowired
    private Calculator calculator;

    @Test
    public void add_correctAnswer(){
        double n1 = 10;
        double n2 = 20;
        double addResult = calculator.add(n1, n2);
        assertEquals(30, addResult);
    }

    @Test
    public void add_correctAnswer_ifMultipleTests(){
        assertAll(
                ()->assertEquals(3, calculator.add(1, 2)),
                ()->assertEquals(10, calculator.add(3, 7)),
                ()->assertEquals(0, calculator.add(-5, 5))
        );
    }

    @Test
    public void divide_shouldThrowArithmeticException_ifN2EqualsZero(){
        double n1 = 10;
        double n2 = 0;
        assertThrows(ArithmeticException.class, ()->calculator.divide(n1,n2));
    }

    @Test
    public void isPositive_true_ifNIsMoreThenZero(){
        double n = 10;
        assertTrue(calculator.isPositive(n));
    }

    @Test
    public void isPositive_false_ifNIsLessThenZero(){
        double n = -10;
        assertFalse(calculator.isPositive(n));
    }

    @Test
    public void subtract_correctAnswer(){
        double n1 = 10;
        double n2 = 3;
        double subtractResult = calculator.subtract(n1, n2);
        assertEquals(7, subtractResult);
    }
}
