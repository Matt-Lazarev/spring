package com.lazarev.springaop.repository;

import com.lazarev.springaop.annotation.Inject;
import com.lazarev.springaop.annotation.Transactional;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

@Component
public class MyRepositoryImpl implements MyRepository {
    @Inject("jdbc:postgresql://localhost:5432/aop")
    private String databaseUrl;

    @PostConstruct
    public void init(){
        System.out.println(databaseUrl);
    }

    @Transactional
    public void insert(){
        System.out.println("Insert into DB: " + databaseUrl);
    }
}
