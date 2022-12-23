package com.lazarev.jpa;

import com.lazarev.jpa.config.JpaConfig;
import com.lazarev.jpa.relations.onetoone.Passport;
import com.lazarev.jpa.relations.onetoone.Person;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ApplicationMainJpaContext {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(JpaConfig.class);

        EntityManagerFactory factory = context.getBean(EntityManagerFactory.class);
        EntityManager entityManager = factory.createEntityManager();



        /* Working with JPA context*/
//        entityManager.getTransaction().begin();
//
//        //transient
//        Person person = new Person(1, "Kate");
//
//        //persistent
//        entityManager.persist(person);
//
//        //removed
//        entityManager.remove(person);
//
//        entityManager.getTransaction().commit();
//
//        //detached, person is still in DB
//        entityManager.detach(person);
//        person.setName("Bob");



        /*Removing a detached instance*/
//        entityManager.getTransaction().begin();
//
//        //detached
//        Person detachedPerson = new Person(1, "Mike");
//        entityManager.remove(detachedPerson);
//
//        entityManager.getTransaction().commit();
//        entityManager.close();


        /* Merge */
//        entityManager.getTransaction().begin();
//
//        //Load in context
//        Person person = new Person(1, "Bob");
//        entityManager.merge(person);
//
//        entityManager.getTransaction().commit();
//        entityManager.close();


        /* Refresh */
        entityManager.getTransaction().begin();

        //Load in context
        Person person = entityManager.find(Person.class, 1);
        person.setName("Bob");

        entityManager.refresh(person);

        entityManager.getTransaction().commit();
        entityManager.close();
    }
}
