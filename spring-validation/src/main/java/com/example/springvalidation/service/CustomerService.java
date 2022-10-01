package com.example.springvalidation.service;

import com.example.springvalidation.entity.Customer;
import com.example.springvalidation.exception.NoSuchCustomerException;
import com.example.springvalidation.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerService {
    private final CustomerRepository customerRepository;

    public List<Customer> getAllCustomers(){
        return customerRepository.findAll();
    }

    public Customer getCustomerById(Long id){
        return customerRepository.findById(id)
                .orElseThrow(()->new NoSuchCustomerException(
                        "Customer with id = '%d' not found".formatted(id)
                ));
    }

    public void saveCustomer(Customer customer){
        customerRepository.save(customer);
    }
}
