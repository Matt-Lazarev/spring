package com.example.springtest.repository;

import com.example.springtest.entity.Customer;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.DataIntegrityViolationException;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@DataJpaTest
class CustomerRepositoryTest {

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    EntityManager entityManager;

    @BeforeEach
    void init(){
        Customer customer1 = new Customer
                (1L, "Kate", "W.", "kate@mail.ru", 40);
        Customer customer2 = new Customer
                (2L, "Bob", "T.", "bob@mail.ru", 20);
        customerRepository.saveAll(List.of(customer1, customer2));
    }

    @AfterEach
    void destroy(){
        entityManager.clear();
        entityManager
                .createNativeQuery("TRUNCATE TABLE customers RESTART IDENTITY")
                .executeUpdate();
    }

    @Test
    void findAll_notEmptyList_ifFindAllCalled(){
        List<Customer> customers = customerRepository.findAll();

        assertThat(customers.size()).isGreaterThan(0);
    }

    @Test
    void findById_emptyOptional_ifCustomerDoesntExist(){
        Long id = 10L;

        Optional<Customer> customerOptional = customerRepository.findById(id);

        assertThat(customerOptional.isEmpty()).isTrue();
    }

    @Test
    void findById_notEmptyOptional_ifCustomerExists(){
        Long id = 1L;

        Optional<Customer> customerOptional = customerRepository.findById(id);

        assertThat(customerOptional.isPresent()).isTrue();
    }

    @Test
    void save_newCustomerInDatabase_ifCustomerIsCorrect(){
        Customer customer = new Customer
                (null, "Mike", "W.", "mike@mail.ru", 25);

        Customer savedCustomer = customerRepository.save(customer);

        assertThat(savedCustomer.getId()).isNotNull();
        assertThat(savedCustomer.getFirstname()).isEqualTo("Mike");
    }

    @Test
    void save_DataIntegrityViolationException_ifEmailDuplicates(){
        Customer customer1 = new Customer
                (null, "Mike", "W.", "m@mail.ru", 25);
        Customer customer2 = new Customer
                (null, "Bob", "T.", "m@mail.ru", 30);

        assertThatThrownBy(()-> customerRepository.saveAll(List.of(customer1, customer2)))
                .isInstanceOf(DataIntegrityViolationException.class)
                .hasMessageContaining("could not execute statement")
                .hasMessageContaining("m@mail.ru");
    }

    @Test
    void deleteById_deletedCustomer_ifCustomerExists(){
        Long id = 1L;

        customerRepository.deleteById(id);

        assertThat(customerRepository.findById(id).isEmpty()).isTrue();
    }

    @Test
    void findCustomerByEmail_notEmptyOptional_ifCustomerWithSuchEmailExists(){
        String email = "bob@mail.ru";

        Optional<Customer> customer = customerRepository.findCustomerByEmail(email);

        assertThat(customer.isPresent()).isTrue();
        assertThat(customer.get().getId()).isNotNull();
        assertThat(customer.get().getFirstname()).isEqualTo("Bob");
        assertThat(customer.get().getEmail()).isEqualTo(email);
    }
}