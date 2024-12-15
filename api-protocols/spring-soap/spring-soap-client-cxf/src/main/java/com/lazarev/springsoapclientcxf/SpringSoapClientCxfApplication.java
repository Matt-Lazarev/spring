package com.lazarev.springsoapclientcxf;

import com.lazarev.spring.soap.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SpringSoapClientCxfApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringSoapClientCxfApplication.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(StudentWebService studentClient, CountryWebService countryClient) {
        return args -> {
            String responseHello = studentClient.hello("Mike");
            System.out.println(responseHello);

            StudentRequest student = new StudentRequest();
            student.setName("Bob");
            StudentResponse responseRegister = studentClient.register(student);
            System.out.println(responseRegister.getMessage());

            System.out.println();

            CountryRequest getCountryRequest = new CountryRequest();
            getCountryRequest.setName("Spain");
            CountryResponse responseCountry = countryClient.getCountry(getCountryRequest);
            System.out.println(responseCountry.getCountry().getName());
            System.out.println(responseCountry.getCountry().getCapital());
        };
    }

}
