package com.example.dto.controller;

import com.example.dto.dto.CustomerDto;
import com.example.dto.entity.Customer;
import com.example.dto.repository.CustomerRepository;
import lombok.AllArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/customers")
@AllArgsConstructor
@Transactional
public class CustomerController {

    private final CustomerRepository customerRepository;

    @GetMapping("/entity")
    public List<Customer> getAllCustomers(){
        return customerRepository.findAll()
                .stream()
                .peek(c -> c.setEmail("new email"))
                .collect(Collectors.toList());
    }

    @GetMapping("/dto")
    public List<CustomerDto> getAllCustomerDtos(){
        return customerRepository.findAllCustomerDtos();
    }
}
