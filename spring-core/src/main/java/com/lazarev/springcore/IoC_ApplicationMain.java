package com.lazarev.springcore;

import com.lazarev.springcore.config.AnnotationConfig;
import com.lazarev.springcore.objects.Cat;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class IoC_ApplicationMain {
    public static void main(String[] args) {
        /*XML-context*/
//        ClassPathXmlApplicationContext xmlContext =
//                new ClassPathXmlApplicationContext("application-context.xml");

        /*JavaCode-context*/
//        AnnotationConfigApplicationContext javaCodeContext =
//                new AnnotationConfigApplicationContext(JavaCodeConfig.class);

        /*Annotation-context*/
        AnnotationConfigApplicationContext annotationContext =
                new AnnotationConfigApplicationContext(AnnotationConfig.class);

        Cat cat1 = (Cat) annotationContext.getBean("pet"); //по id
        Cat cat2 = annotationContext.getBean(Cat.class); //по классу
        System.out.println(cat1);
        System.out.println(cat2);

        annotationContext.close();
    }
}
