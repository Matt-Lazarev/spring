package com.lazarev.springthriftstartermaven.service;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@Service
public class Calculator {

    public int add(int n1, int n2) {
        return n1 + n2;
    }

    public double divide(int n1, int n2) {
        if(n2 == 0) {
            throw new ArithmeticException("Cannot divide by zero: n2 is equal to 0");
        }

        return (double) n1 / n2;
    }

    public List<Byte> digits(int num) {
        LinkedList<Byte> digits = new LinkedList<>();
        while (num != 0) {
            byte digit = (byte) (num % 10);
            num /= 10;
            digits.addFirst(digit);
        }
        return digits;
    }
}
