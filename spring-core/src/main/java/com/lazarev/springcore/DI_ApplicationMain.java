package com.lazarev.springcore;

import com.lazarev.springcore.config.JavaCodeConfig;
import com.lazarev.springcore.objects.*;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class DI_ApplicationMain {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(JavaCodeConfig.class);

        System.out.println(context.getBean(Person.class).getPet());
        System.out.println(context.getBean(Car.class));

        context.close();
    }
}
