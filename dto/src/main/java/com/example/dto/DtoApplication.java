package com.example.dto;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;
import java.math.RoundingMode;

@SpringBootApplication
public class DtoApplication {

    public static void main(String[] args) {
        //SpringApplication.run(DtoApplication.class, args);

        BigDecimal n = new BigDecimal("10.5");

        System.out.println(n.intValue());      //10
        System.out.println(n.setScale(0, RoundingMode.HALF_UP)); //11


    }

}
