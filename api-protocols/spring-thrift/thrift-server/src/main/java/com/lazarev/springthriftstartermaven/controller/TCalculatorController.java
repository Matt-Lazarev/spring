package com.lazarev.springthriftstartermaven.controller;

import com.lazarev.springthriftstartermaven.service.CalculatorAdapter;
import com.lazarev.thrift.service.calculator.CalculatorResult;
import com.lazarev.thrift.service.calculator.CalculatorService;
import lombok.RequiredArgsConstructor;
import ru.trylogic.spring.boot.thrift.annotation.ThriftController;

@ThriftController("/api/calculator")
@RequiredArgsConstructor
public class TCalculatorController implements CalculatorService.Iface {
    private final CalculatorAdapter calculatorAdapter;

    @Override
    public CalculatorResult add(int n1, int n2) {
        return calculatorAdapter.add(n1, n2);
    }

    @Override
    public CalculatorResult div(int n1, int n2) {
        return calculatorAdapter.divide(n1, n2);
    }

    @Override
    public CalculatorResult digits(int num) {
        return calculatorAdapter.digits(num);
    }
}
