package com.example.springtest.controller;

import com.example.springtest.entity.Customer;
import com.example.springtest.exception.NoSuchCustomerException;
import com.example.springtest.service.CustomerService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(CustomerController.class)
class CustomerControllerTest {

    @Autowired
    MockMvc mvc;

    @MockBean
    CustomerService customerService;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    void getAllCustomers_statusOk_ifCustomersExist() throws Exception {
        Customer customer = new Customer
                (1L, "Mike", "W.", "mike@mail.ru", 25);

        when(customerService.getAllCustomers())
                .thenReturn(List.of(customer));

        mvc.perform(get("/api/customers"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").exists())
                .andExpect(jsonPath("$[0].firstname").value("Mike"))
                .andExpect(jsonPath("$[0].age").isNumber());
    }

    @Test
    void getAllCustomers_statusNotFound_ifCustomersDontExist() throws Exception {
        when(customerService.getAllCustomers())
                .thenReturn(List.of());

        mvc.perform(get("/api/customers"))
                .andDo(print())
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$[0].id").doesNotExist());
    }

    @Test
    void getCustomerByID_throwNoSuchCustomerException_ifIdIsIncorrect() throws Exception {
        when(customerService.getCustomerById(anyLong()))
                .thenThrow(NoSuchCustomerException.class);

        mvc.perform(get("/api/customers/1"))
                .andDo(print())
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.id").doesNotExist())
                .andExpect(jsonPath("$.status").value("NOT_FOUND"));
    }

    @Test
    void getCustomerByID_customerWithCorrectId_ifCustomerExists() throws Exception {
        Customer customer = new Customer(1L, "Mike", "W.", "mike@mail.ru", 25);

        when(customerService.getCustomerById(anyLong()))
                .thenReturn(customer);

        mvc.perform(get("/api/customers/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.firstname").value("Mike"))
                .andExpect(jsonPath("$.age").value(25));
    }

    @Test
    void headerMethod_statusOk_ifRequestHeaderIsPresent() throws Exception {
        mvc.perform(get("/api/customers/header")
                        .header("Authorization", "123"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void headerMethod_statusBadRequest_ifRequestHeaderIsAbsent() throws Exception {
        mvc.perform(get("/api/customers/header"))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    void headerMethod_statusOk_ifResponseHeaderIsPresent() throws Exception {
        mvc.perform(get("/api/customers/header")
                        .header("Authorization", "123"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(header().stringValues("Authorization", "321"));
    }

    @Test
    void saveCustomer_statusCreated_ifRequestIsCorrect() throws Exception {
        Customer customer = new Customer
                (null, "Mike", "W.", "mike@mail.ru", 25);

        doNothing()
                .when(customerService)
                .saveCustomer(any(Customer.class));

        mvc.perform(post("/api/customers")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(objectMapper.writeValueAsString(customer)))
                .andDo(print())
                .andExpect(status().isCreated());
    }

    @Test
    void updateCustomerById_statusOk_ifCustomerExists() throws Exception {
        Customer customer = new Customer
                (null, "Mike", "W.", "mike@mail.ru", 25);

        doNothing()
                .when(customerService)
                .updateCustomerById(any(Customer.class), anyLong());

        mvc.perform(put("/api/customers/1")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(objectMapper.writeValueAsString(customer)))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void updateCustomerById_statusNotFound_ifCustomerDoesntExist() throws Exception {
        Customer customer = new Customer(1L, "Mike", "W.", "mike@mail.ru", 25);

        doThrow(NoSuchCustomerException.class)
                .when(customerService)
                .updateCustomerById(any(Customer.class), anyLong());

        mvc.perform(put("/api/customers/1")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(objectMapper.writeValueAsString(customer)))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    void deleteCustomerById_statusOk_ifCustomerExists() throws Exception {
        doNothing()
                .when(customerService)
                .deleteCustomerById(anyLong());

        mvc.perform(delete("/api/customers/1"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void deleteCustomerById_statusNotFound_ifCustomerDoesntExist() throws Exception {
        doThrow(NoSuchCustomerException.class)
                .when(customerService)
                .deleteCustomerById(anyLong());

        mvc.perform(delete("/api/customers/1"))
                .andDo(print())
                .andExpect(status().isNotFound());
    }
}