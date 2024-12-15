package com.lazarev.jpa;

import com.lazarev.jpa.config.JpaConfig;
import com.lazarev.jpa.relations.onetomany.Department;
import com.lazarev.jpa.relations.onetomany.Employee;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

public class ApplicationMainOrphanRemoval {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(JpaConfig.class);

        EntityManagerFactory factory = context.getBean(EntityManagerFactory.class);
        EntityManager entityManager = factory.createEntityManager();

        entityManager.getTransaction().begin();

        Department department = entityManager.find(Department.class, 1);
        department.getEmployees().remove(0);

        entityManager.getTransaction().commit();
        entityManager.close();
    }
}
