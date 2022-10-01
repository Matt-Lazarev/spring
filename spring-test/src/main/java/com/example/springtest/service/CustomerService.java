package com.example.springtest.service;

import com.example.springtest.entity.Customer;
import com.example.springtest.exception.NoSuchCustomerException;
import com.example.springtest.repository.CustomerRepository;
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

    public void updateCustomerById(Customer newCustomer, Long id){
        Customer updatedCustomer = customerRepository.findById(id)
                .orElseThrow(()->new NoSuchCustomerException(
                    "Customer with id = '%d' not found".formatted(id)));
        updatedCustomer.setFirstname(newCustomer.getFirstname());
        updatedCustomer.setLastname(newCustomer.getLastname());
        updatedCustomer.setAge(newCustomer.getAge());
        customerRepository.save(updatedCustomer);
    }

    public void deleteCustomerById(Long id){
        if(!customerRepository.existsById(id)){
            throw new NoSuchCustomerException(
                    "Customer with id = '%d' not found".formatted(id));
        }

        customerRepository.deleteById(id);
    }
}
