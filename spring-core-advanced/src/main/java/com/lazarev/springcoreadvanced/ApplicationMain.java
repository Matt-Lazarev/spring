package com.lazarev.springcoreadvanced;

import com.lazarev.springcoreadvanced.config.AppConfig;
import com.lazarev.springcoreadvanced.repository.PersonRepository;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ApplicationMain {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        PersonRepository bean = context.getBean(PersonRepository.class);
        //bean.update();
    }
}
