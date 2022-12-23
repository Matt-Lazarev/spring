package com.lazarev.jpa.config;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.hibernate.jpa.boot.internal.EntityManagerFactoryBuilderImpl;
import org.hibernate.jpa.boot.internal.PersistenceUnitInfoDescriptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Primary;

import java.util.Map;

@ComponentScan("com.lazarev.jpa")
public class JpaConfig {

    @Primary
    @Bean
    public EntityManagerFactory entityManagerFactory(){
        return Persistence.createEntityManagerFactory("my-persistence-unit");
    }

    @Bean
    public EntityManagerFactory entityManagerFactoryJavaCode(MyPersistenceUnit persistenceUnit){
        PersistenceUnitInfoDescriptor puid =
                new PersistenceUnitInfoDescriptor(persistenceUnit);
        return new EntityManagerFactoryBuilderImpl(puid, Map.of()).build();
    }
}
