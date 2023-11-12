package com.lazarev.springaop;

import com.lazarev.springaop.repository.MyRepository;
import com.lazarev.springaop.service.Calculator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class SpringAopApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(SpringAopApplication.class, args);
		MyRepository repository = context.getBean(MyRepository.class);
		repository.insert();

		Calculator calculator = context.getBean(Calculator.class);
		int add = calculator.add(1, 2);
		int sub = calculator.sub(1, 2);
	}
}
