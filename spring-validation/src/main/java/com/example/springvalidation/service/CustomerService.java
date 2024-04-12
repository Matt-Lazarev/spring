package com.example.springvalidation.service;

import com.example.springvalidation.dto.CustomerDto;
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
    private final CustomerMapper customerMapper;

    public List<CustomerDto> getAllCustomers(){
        List<Customer> customers = customerRepository.findAll();
        return customerMapper.toDtoList(customers);
    }

    public CustomerDto getCustomerById(Long id){
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new NoSuchCustomerException(
                        "Customer with id = '%d' not found".formatted(id)
                ));
        return customerMapper.toDto(customer);
    }

    public void saveCustomer(CustomerDto customerDto){
        Customer customer = customerMapper.toEntity(customerDto);
        customerRepository.save(customer);
    }
}
