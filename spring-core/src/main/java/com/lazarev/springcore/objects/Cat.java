package com.lazarev.springcore.objects;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Component("pet")
public class Cat {
    public void say(){
        System.out.println("Meow");
    }

    public Cat(){
        System.out.println("constructor");
    }

    @PostConstruct
    public void init(){
        System.out.println("init");
    }

    @PreDestroy
    public void destroy(){
        System.out.println("destroy");
    }
}

