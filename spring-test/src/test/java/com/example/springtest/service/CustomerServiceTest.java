package com.example.springtest.service;

import com.example.springtest.entity.Customer;
import com.example.springtest.exception.NoSuchCustomerException;
import com.example.springtest.repository.CustomerRepository;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.atMostOnce;

@SpringBootTest
class CustomerServiceTest {

    @Autowired
    CustomerService customerService;

    @MockBean
    CustomerRepository customerRepository;

    @Captor
    ArgumentCaptor<Customer> captor;

    @Test
    void getAllCustomers_notEmptyList_ifCustomersExist(){
        Customer customer = new Customer
                (1L, "Mike", "W.", "mike@mail.ru", 25);

        when(customerRepository.findAll())
                .thenReturn(List.of(customer));

        List<Customer> customers = customerService.getAllCustomers();

        verify(customerRepository).findAll();
        assertThat(customers.size()).isEqualTo(1);
        assertThat(customers.get(0).getEmail()).isEqualTo("mike@mail.ru");
    }

    @Test
    void getAllCustomers_emptyList_ifCustomersDontExist(){
        when(customerRepository.findAll())
                .thenReturn(List.of());

        List<Customer> customers = customerService.getAllCustomers();

        verify(customerRepository).findAll();
        assertThat(customers.size()).isEqualTo(0);
    }

    @Test
    void getCustomerById_customerWithCorrectId_ifCustomerExists(){
        Customer customer = new Customer
                (1L, "Mike", "W.", "mike@mail.ru", 25);

        when(customerRepository.findById(anyLong()))
                .thenReturn(Optional.of(customer));

        Customer retrievedCustomer = customerService.getCustomerById(1L);

        verify(customerRepository).findById(anyLong());
        assertThat(retrievedCustomer.getId()).isEqualTo(customer.getId());
        assertThat(retrievedCustomer.getEmail()).isEqualTo(customer.getEmail());
        assertThat(retrievedCustomer.getFirstname()).isEqualTo(customer.getFirstname());
    }

    @Test
    void getCustomerById_NoSuchCustomerException_ifCustomerDoesntExist(){
        when(customerRepository.findById(anyLong()))
                .thenReturn(Optional.empty());

        assertThatThrownBy(()->customerService.getCustomerById(1L))
                .isInstanceOf(NoSuchCustomerException.class)
                .hasMessage("Customer with id = '1' not found");
    }

    @Test
    void saveCustomer_repositorySaveMethodCalled_ifCorrectCustomerParameter(){
        Customer customer = new Customer
                (null, "Mike", "W.", "mike@mail.ru", 25);

        when(customerRepository.save(any(Customer.class)))
                .thenReturn(customer);

        customerService.saveCustomer(customer);

        verify(customerRepository, only()).save(any(Customer.class));
    }

    @Test
    void updateCustomerById_updatedCustomer_ifCustomerExists(){
        Customer newCustomer = new Customer
                (null, "Mike", "Watson", "mike@mail.ru", 30);
        Customer updatedCustomer = new Customer
                (1L, "Mike", "W.", "mike@mail.ru", 25);
        Long id = 1L;

        when(customerRepository.findById(anyLong()))
                .thenReturn(Optional.of(updatedCustomer));

        customerService.updateCustomerById(newCustomer, id);

        verify(customerRepository).save(captor.capture());
        Customer capturedCustomer = captor.getValue();

        assertThat(capturedCustomer.getId()).isEqualTo(id);
        assertThat(capturedCustomer.getLastname()).isEqualTo(newCustomer.getLastname());
        assertThat(capturedCustomer.getAge()).isEqualTo(newCustomer.getAge());
    }

    @Test
    void updateCustomerById_NoSuchCustomerException_ifCustomerDoesntExist(){
        Customer newCustomer = new Customer
                (null, "Mike", "Watson", "mike@mail.ru", 30);
        Long id = 1L;

        when(customerRepository.findById(anyLong()))
                .thenReturn(Optional.empty());

        assertThatThrownBy(()->customerService.updateCustomerById(newCustomer, id))
                .isInstanceOf(NoSuchCustomerException.class)
                .hasMessage("Customer with id = '1' not found");
        verify(customerRepository, never()).save(any(Customer.class));
    }

    @Test
    void deleteById_deletedCustomer_ifCustomerExists(){
        Long id = 1L;

        when(customerRepository.existsById(anyLong()))
                .thenReturn(true);

        doNothing()
                .when(customerRepository)
                .deleteById(anyLong());

        customerService.deleteCustomerById(id);

        verify(customerRepository, atMostOnce()).existsById(anyLong());
        verify(customerRepository, atMostOnce()).deleteById(anyLong());
        verifyNoMoreInteractions(customerRepository);
    }
}