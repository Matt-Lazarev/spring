package com.lazarev.hibernate.config;

import com.lazarev.hibernate.entity.Product;
import com.lazarev.hibernate.relations.manytomany.Subject;
import com.lazarev.hibernate.relations.manytomany.Teacher;
import com.lazarev.hibernate.relations.onetomany.Department;
import com.lazarev.hibernate.relations.onetomany.Employee;
import com.lazarev.hibernate.relations.onetoone.Passport;
import com.lazarev.hibernate.relations.onetoone.Person;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Primary;

import java.util.Properties;

@ComponentScan("com.lazarev.hibernate")
public class AppConfiguration {

    /*XML Configuration */

    @Bean
    public SessionFactory sessionFactory(){
        return new Configuration()
                .configure("hibernate-config.xml")
                .addAnnotatedClass(Product.class)
                .buildSessionFactory();
    }


    /*Java Code configuration*/
    @Bean
    public SessionFactory sessionFactory(Configuration configuration){
        ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                .applySettings(configuration.getProperties()).build();
        return configuration.buildSessionFactory(serviceRegistry);
    }

    @Primary
    @Bean
    public Configuration configuration(){
        Configuration configuration = new Configuration();

        Properties properties = new Properties();
        properties.put(Environment.URL, "jdbc:mysql://localhost:3306/lessonhibernate");
        properties.put(Environment.USER, "root");
        properties.put(Environment.PASS, "rootroot");
        properties.put(Environment.DRIVER, "com.mysql.cj.jdbc.Driver");
        properties.put(Environment.DIALECT, "org.hibernate.dialect.MySQLDialect");
        properties.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");
        properties.put(Environment.HBM2DDL_AUTO, "update");
        properties.put(Environment.SHOW_SQL, "true");
        properties.put(Environment.FORMAT_SQL, "false");
        properties.put(Environment.GLOBALLY_QUOTED_IDENTIFIERS, "true");
        configuration.setProperties(properties);
        configuration.addAnnotatedClass(Product.class);
        configuration.addAnnotatedClass(Person.class);
        configuration.addAnnotatedClass(Passport.class);
        configuration.addAnnotatedClass(Employee.class);
        configuration.addAnnotatedClass(Department.class);
        configuration.addAnnotatedClass(Teacher.class);
        configuration.addAnnotatedClass(Subject.class);

        return configuration;
    }
}
