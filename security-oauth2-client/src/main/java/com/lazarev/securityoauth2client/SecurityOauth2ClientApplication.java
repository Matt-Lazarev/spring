package com.lazarev.securityoauth2client;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class SecurityOauth2ClientApplication {
    public static void main(String[] args) {
        SpringApplication.run(SecurityOauth2ClientApplication.class, args);
    }
}
