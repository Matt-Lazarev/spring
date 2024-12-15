package com.example.springvalidation.service;

import com.example.springvalidation.dto.CustomerDto;
import com.example.springvalidation.entity.Customer;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CustomerMapper {
    CustomerDto toDto(Customer customer);
    Customer toEntity(CustomerDto customerDto);
    List<CustomerDto> toDtoList(List<Customer> customers);
}
