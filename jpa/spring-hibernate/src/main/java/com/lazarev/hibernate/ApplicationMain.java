package com.lazarev.hibernate;

import com.lazarev.hibernate.config.AppConfiguration;
import com.lazarev.hibernate.entity.Product;
import com.lazarev.hibernate.relations.manytomany.Subject;
import com.lazarev.hibernate.relations.manytomany.Teacher;
import com.lazarev.hibernate.relations.onetomany.Department;
import com.lazarev.hibernate.relations.onetomany.Employee;
import com.lazarev.hibernate.relations.onetoone.Passport;
import com.lazarev.hibernate.relations.onetoone.Person;
import com.lazarev.hibernate.repository.ProductRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.time.LocalDate;
import java.util.List;

public class ApplicationMain {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfiguration.class);

        /*Lesson 1*/
//        ProductRepository repository = context.getBean(ProductRepository.class);
//        Product product = new Product(null, "Milk", 150, LocalDate.of(2022, 5, 31));
//        repository.saveProduct(product);
//        System.out.println(repository.getProductById(1L));

        /*Lesson 2*/
        SessionFactory sessionFactory = context.getBean(SessionFactory.class);

        Session session = sessionFactory.getCurrentSession();
        session.getTransaction().begin();

        /* one-to-one */
//        Person person = new Person(null, "Kate");
//        Passport passport = new Passport(null, 1234, 56);
//        person.setPassport(passport);
//        session.persist(person);

        /* one-to-many */
//        Department department = new Department(null, "IT");
//        List<Employee> employees = List.of(
//                new Employee(null, "Mike", "Java"),
//                new Employee(null, "Bob", "C++"));
//
//        department.setEmployees(employees);
//        session.persist(department);

        /* many-to-many */
//        Teacher t1 = new Teacher(null, "Mike", 5);
//        Teacher t2 = new Teacher(null, "Kate", 10);
//
//        Subject s1 = new Subject(null, "Math");
//        Subject s2 = new Subject(null, "History");
//        Subject s3 = new Subject(null, "Informatics");
//
//        t1.setSubjects(List.of(s1, s2));
//        t2.setSubjects(List.of(s2, s3));
//
//        session.persist(t1);
//        session.persist(t2);

        /* one-to-one bi-directional */
//        Person person = new Person(null, "Kate");
//        Passport passport = new Passport(null, 1234, 56);
//
//        passport.setPerson(person);
//        session.persist(passport);

        /* one-to-many bi-directional */
//        Department department = new Department(null, "IT");
//        List<Employee> employees = List.of(
//                new Employee(null, "Mike", "Java"),
//                new Employee(null, "Bob", "C++"));
//
//        for(Employee employee : employees){
//            employee.setDepartment(department);
//            session.persist(employee);
//        }

        /* many-to-many bi-directional */
//        Teacher t1 = new Teacher(null, "Mike", 5);
//        Teacher t2 = new Teacher(null, "Kate", 10);
//
//        Subject s1 = new Subject(null, "Math");
//        Subject s2 = new Subject(null, "History");
//        Subject s3 = new Subject(null, "Informatics");

//        s1.setTeachers(List.of(t1,t2));
//        s2.setTeachers(List.of(t1));
//        s3.setTeachers(List.of(t2));

//        s1.addTeacher(t1);
//        s1.addTeacher(t2);
//        s2.addTeacher(t1);
//        s3.addTeacher(t2);
//
//        session.persist(s1);
//        session.persist(s2);
//        session.persist(s3);

        /* Eager Lazy */
        Passport passport = session.get(Passport.class, 1);
        System.out.println(passport.getSeries());

//        Person person = session.get(Person.class, 1);
//        System.out.println(person.getName());

        session.getTransaction().commit();
        context.close();
    }
}
