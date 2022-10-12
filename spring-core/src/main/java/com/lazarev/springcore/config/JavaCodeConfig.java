package com.lazarev.springcore.config;

import com.lazarev.springcore.objects.Cat;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;

public class JavaCodeConfig {
    @Bean
    @Scope("prototype")
    public Cat pet(){
        return new Cat();
    }
}
