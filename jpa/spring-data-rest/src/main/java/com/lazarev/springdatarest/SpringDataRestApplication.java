package com.lazarev.springdatarest;

import com.lazarev.springdatarest.entity.Customer;
import com.lazarev.springdatarest.repository.CustomerRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.ApplicationPidFileWriter;
import org.springframework.boot.web.context.WebServerPortFileWriter;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;

@SpringBootApplication
public class SpringDataRestApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(SpringDataRestApplication.class, args);

        CustomerRepository repository = context.getBean(CustomerRepository.class);

        Pageable pageable = PageRequest.of(0, 2);
        List<Customer> customersPage = repository.findAllByFirstname(pageable);

        Sort sort = Sort.by(Sort.Direction.DESC, "firstname");
        List<Customer> customersSort = repository.sortAll(sort);
    }
}
