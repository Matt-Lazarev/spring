package com.example.springvalidation.controller;

import com.example.springvalidation.dto.CustomerDto;
import com.example.springvalidation.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/customers")
@RequiredArgsConstructor
public class CustomerController {
    private final CustomerService customerService;

    @GetMapping
    public ResponseEntity<List<CustomerDto>> getAllCustomers(){
        List<CustomerDto> customers = customerService.getAllCustomers();
        if(!customers.isEmpty()){
            return ResponseEntity.ok(customers);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerDto> getCustomerById(@PathVariable Long id){
        return ResponseEntity.ok(customerService.getCustomerById(id));
    }

    @PostMapping
    public ResponseEntity<?> saveCustomer(@Valid @RequestBody CustomerDto customer){
        customerService.saveCustomer(customer);
        return ResponseEntity.ok().build();
    }
}
