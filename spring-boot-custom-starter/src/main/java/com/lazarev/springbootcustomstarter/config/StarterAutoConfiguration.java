package com.lazarev.springbootcustomstarter.config;

import com.lazarev.springbootcustomstarter.model.Person;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("com.lazarev.springbootcustomstarter")
public class StarterAutoConfiguration {
//    @Bean
//    public Person person(){
//        return new Person();
//    }
}
