package com.lazarev.springcoreadvanced.repository;

import com.lazarev.springcoreadvanced.annotation.InTransaction;
import com.lazarev.springcoreadvanced.annotation.PostProxy;
import org.springframework.stereotype.Component;

@InTransaction
@Component
public class PersonRepositoryImpl implements PersonRepository{
    public void update(){
        System.out.println("updated person");
    }

    @PostProxy
    public void init(){
        update();
    }
}
