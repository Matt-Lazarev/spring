package com.lazarev.springcore.config;

import com.lazarev.springcore.objects.Cat;
import com.lazarev.springcore.objects.Person;
import com.lazarev.springcore.objects.Pet;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Scope;

@SuppressWarnings("all")
@ComponentScan("com.lazarev.springcore")
public class JavaCodeConfig {
    @Bean
    @Scope("prototype")
    public Cat cat(){
        return new Cat();
    }

    @Bean
    public Person person(){
        return new Person(pet());
    }

    @Bean
    public Pet pet(){
        return new Cat();
    }
}


