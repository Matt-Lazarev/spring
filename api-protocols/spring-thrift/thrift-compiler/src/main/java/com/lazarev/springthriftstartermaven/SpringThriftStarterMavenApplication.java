package com.lazarev.springthriftstartermaven;

import com.lazarev.thrift.service.samples.Person;
import com.lazarev.thrift.service.samples.TPaymentRequest;
import com.lazarev.thrift.service.samples.TPaymentType;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.UUID;

@SpringBootApplication
public class SpringThriftStarterMavenApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringThriftStarterMavenApplication.class, args);
    }

    @Bean
    CommandLineRunner clr() {
        return args -> {
            TPaymentType type = new TPaymentType();
            type.setCrypto(UUID.randomUUID().toString());

            TPaymentRequest paymentRequest = new TPaymentRequest();

            Person person = new Person();
            System.out.println(person.getName() + " " + person.isSetName());
        };
    }

}
