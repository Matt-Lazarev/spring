package com.lazarev.securitykeycloakclient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class SecurityKeycloakClientApplication {
	public static void main(String[] args) {
		SpringApplication.run(SecurityKeycloakClientApplication.class, args);
	}
}
