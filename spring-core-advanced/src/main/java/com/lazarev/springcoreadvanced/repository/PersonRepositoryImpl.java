package com.lazarev.springcoreadvanced.repository;

import com.lazarev.springcoreadvanced.annotation.InTransaction;
import com.lazarev.springcoreadvanced.annotation.PostProxy;
import org.springframework.stereotype.Component;

@Component
public class PersonRepositoryImpl implements PersonRepository{
    @Override
    public void update(){
        System.out.println("updated person");
    }

    @PostProxy
    @Override
    public void init(){
        update();
    }

    @Override
    @InTransaction
    public void test1() {
        System.out.println("test1");
    }

    @Override
    @InTransaction
    public void test2() {
        System.out.println("test2");
    }
}
