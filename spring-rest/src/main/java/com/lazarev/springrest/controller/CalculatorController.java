package com.lazarev.springrest.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashSet;

@RestController
public class CalculatorController {
    private CalculatorRepository calculatorRepository;

    @GetMapping("/calculate/mul")
    public int calculate(@RequestParam("p1") int p1,
                         @RequestParam("p2") int p2){

        int result = p1 * p2;
        calculatorRepository.save(result);

        return result;
    }
}

class CalculatorRepository {
    void save(int result){}

    public static void main(String[] args) {
        HashSet<Object> set = new HashSet<>();

        Object o = new Object();


        set.add(1);
        set.add("123");
        set.add(o);

        System.out.println(set.contains(o));
        System.out.println(set.contains("123"));
    }
}