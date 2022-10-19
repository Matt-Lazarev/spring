package com.lazarev.springcore.objects;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Car {

    @Value("Kia")
    private String model;

    @Value("100")
    private int maxSpeed;

    @Value("20.5")
    private double fuelCapacity;

    @Override
    public String toString() {
        return "Car{" +
                "model='" + model + '\'' +
                ", maxSpeed=" + maxSpeed +
                ", fuelCapacity=" + fuelCapacity +
                '}';
    }
}
