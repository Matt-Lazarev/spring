package com.lazarev.springdatajpacompositepk;

import com.lazarev.springdatajpacompositepk.entity.embedded.Order;
import com.lazarev.springdatajpacompositepk.entity.embedded.OrderId;
import com.lazarev.springdatajpacompositepk.entity.idclass.Company;
import com.lazarev.springdatajpacompositepk.entity.idclass.CompanyId;
import com.lazarev.springdatajpacompositepk.entity.relation.emdedded.Passport;
import com.lazarev.springdatajpacompositepk.entity.relation.emdedded.PassportId;
import com.lazarev.springdatajpacompositepk.entity.relation.emdedded.Person;
import com.lazarev.springdatajpacompositepk.entity.relation.idclass.Claim;
import com.lazarev.springdatajpacompositepk.entity.relation.idclass.Description;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.transaction.support.TransactionTemplate;

import java.util.UUID;

@SpringBootApplication
@RequiredArgsConstructor
public class SpringDataJpaCompositePkApplication {
    private final EntityManager entityManager;
    private final TransactionTemplate transactionTemplate;

    @Bean
    CommandLineRunner cmr() {
        return args -> {
            transactionTemplate.executeWithoutResult((a) -> {
                saveRelationWithIdClass();
            });
        };
    }

    private void saveWithIdClass() {
        Company company = new Company();
        company.setName("Corp");
        entityManager.persist(company); //ID can be auto-generated

        System.out.println(
                entityManager.find(Company.class, new CompanyId(1, 1))
        );
    }

    private void saveWithEmbeddedId() {
        Order order = new Order();
        order.setOrderId(new OrderId(1, 1));
        entityManager.persist(order); //Set ID manually

        System.out.println(
                entityManager.find(Order.class, new OrderId(1, 1))
        );
    }

    private void saveRelationWithEmbeddedId() {
        Passport passport = new Passport();
        passport.setPassportId(new PassportId("123", "456"));
        passport.setCountry("RU");

        Person person = new Person();
        person.setName("Mike");
        person.setPassport(passport);

        entityManager.persist(person);
    }

    private void saveRelationWithIdClass() {
        Description description = new Description();
        description.setOwnerId(UUID.randomUUID());
        description.setText("Text");

        Claim claim = new Claim();
        claim.setName("CLAIM");
        claim.setDescription(description);

        entityManager.persist(claim);
    }

    public static void main(String[] args) {
        SpringApplication.run(SpringDataJpaCompositePkApplication.class, args);
    }
}
