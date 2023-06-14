package com.lazarev.springcoreadvanced.model;

import com.lazarev.springcoreadvanced.annotation.InjectRandomInt;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class Person {
    @Value("Mike")
    private String name;

    @InjectRandomInt(min=0, max=100)
    private int age;

    public Person(){
        //System.out.println(age);
    }

    public void setName(String name) {
        this.name = name;
    }

    @PostConstruct
    public void init(){
        //System.out.println(age);
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
