package com.lazarev.jpa;

import com.lazarev.jpa.config.JpaConfig;
import com.lazarev.jpa.relations.onetomany.Department;
import com.lazarev.jpa.relations.onetomany.Employee;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

public class ApplicationMainCascadeTypes {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(JpaConfig.class);

        EntityManagerFactory factory = context.getBean(EntityManagerFactory.class);
        EntityManager entityManager = factory.createEntityManager();

        /* Cascade Persist */
//        entityManager.getTransaction().begin();
//
//        Department department = new Department(null, "IT");
//        List<Employee> employees = List.of(
//                new Employee(null, "Kate", "Programmer"),
//                new Employee(null, "Mike", "Dev-ops"));
//        department.setEmployees(employees);
//        entityManager.persist(department);
//
//        entityManager.getTransaction().commit();
//        entityManager.close();


        /* Cascade Merge */
//        entityManager.getTransaction().begin();
//
//        Employee emp1 = new Employee(1, "Mike", "Java");
//        Employee emp2 = new Employee(2, "Bob", "Dev-ops");
//
//        Department department = new Department(1, "IT");
//        department.setEmployees(List.of(emp1, emp2));
//        entityManager.merge(department);
//
//        entityManager.getTransaction().commit();
//        entityManager.close();

        /* Cascade Remove */
//        entityManager.getTransaction().begin();
//
//        Department department = entityManager.find(Department.class, 1);
//        entityManager.remove(department);
//
//        entityManager.getTransaction().commit();
//        entityManager.close();


    }
}
