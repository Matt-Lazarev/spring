package com.example.dto.repository;

import com.example.dto.dto.CustomerDto;
import com.example.dto.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {

    @Query("select new com.example.dto.dto.CustomerDto(c.id, c.name, c.email) " +
           "from Customer c")
    List<CustomerDto> findAllCustomerDtos();
}
