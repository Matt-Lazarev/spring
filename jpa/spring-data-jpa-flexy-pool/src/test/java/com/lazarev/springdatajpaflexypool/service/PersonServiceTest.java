package com.lazarev.springdatajpaflexypool.service;

import com.lazarev.springdatajpaflexypool.entity.Person;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class PersonServiceTest {

    @Autowired
    private PersonService personService;

    @Test
    void testTransactionLogs() {
        personService.savePerson(new Person().setName("Mike"));
    }
}