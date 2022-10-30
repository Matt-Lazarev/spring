package com.lazarev.springdatarest.repository;

import com.lazarev.springdatarest.entity.Customer;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

    @Query("from Customer c where c.firstname like '%a%'")
    List<Customer> findAllByFirstname(Pageable pageable);

    @Query("from Customer c")
    List<Customer> sortAll(Sort sort);

}
