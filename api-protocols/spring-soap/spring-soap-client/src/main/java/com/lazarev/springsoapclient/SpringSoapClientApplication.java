package com.lazarev.springsoapclient;

import com.lazarev.spring.soap.GetCountryResponse;
import com.lazarev.springsoapclient.client.CountryClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@Slf4j
@SpringBootApplication
public class SpringSoapClientApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringSoapClientApplication.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner(CountryClient client) {
        return args -> {
            GetCountryResponse response = client.getCountry("Spain");
            log.info("Got response from Countries service: {}", response.getCountry());
            log.info("Parameters: {}, {}", response.getCountry().getName(), response.getCountry().getCapital());
        };
    }

}
