package com.lazarev.springdatajpaflexypool.service;

import com.lazarev.springdatajpaflexypool.entity.Person;
import com.lazarev.springdatajpaflexypool.repository.PersonRepository;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PersonService {
    private final PersonRepository personRepository;

    @Transactional(readOnly = true)
    public Person getPersonById(UUID id) {
        return personRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("No such person"));
    }

    @Transactional
    @SneakyThrows
    public UUID savePerson(Person person) {
        Thread.sleep(200);
        return personRepository.save(person).getId();
    }
}
