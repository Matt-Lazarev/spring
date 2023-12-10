package com.lazarev.springdatajpa;

import com.lazarev.springdatajpa.entity.composite.Company;
import com.lazarev.springdatajpa.entity.composite.CompanyId;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.transaction.support.TransactionTemplate;

@SpringBootApplication
@RequiredArgsConstructor
public class SpringDataJpaApplication {
    private final EntityManager entityManager;
    private final TransactionTemplate transactionTemplate;

    @Bean
    CommandLineRunner cmr(){
        return args -> {
            transactionTemplate.executeWithoutResult((a)->{
                Company company = new Company();
                company.setName("Corp");
                entityManager.persist(company);

                System.out.println(entityManager.find(Company.class, new CompanyId(1, 1)));
            });
        };
    }

    public static void main(String[] args) {
        SpringApplication.run(SpringDataJpaApplication.class, args);
    }
}
