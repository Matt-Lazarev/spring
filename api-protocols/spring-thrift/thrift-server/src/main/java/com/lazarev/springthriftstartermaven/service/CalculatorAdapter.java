package com.lazarev.springthriftstartermaven.service;

import com.lazarev.thrift.service.calculator.CalculatorResult;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CalculatorAdapter {
    private final Calculator calculator;

    public CalculatorResult add(int n1, int n2) {
        int addResult = calculator.add(n1, n2);
        return new CalculatorResult()
                .setOperation("ADD")
                .setResult(addResult);
    }

    public CalculatorResult divide(int n1, int n2) {
        double divideResult = calculator.divide(n1, n2);
        return new CalculatorResult()
                .setOperation("DIVIDE")
                .setResult(divideResult);
    }

    public CalculatorResult digits(int num) {
        List<Byte> digitsResult = calculator.digits(num);
        return new CalculatorResult()
                .setOperation("DIGITS")
                .setArrayResult(digitsResult);
    }
}
