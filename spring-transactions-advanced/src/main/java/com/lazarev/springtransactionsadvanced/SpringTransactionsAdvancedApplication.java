package com.lazarev.springtransactionsadvanced;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.retry.annotation.EnableRetry;

@EnableRetry
@SpringBootApplication
public class SpringTransactionsAdvancedApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringTransactionsAdvancedApplication.class, args);
    }
}
