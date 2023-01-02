package com.lazarev.cloudopenfeign.config.feign;

import com.lazarev.cloudopenfeign.feignclient.PersonClient;
import com.lazarev.cloudopenfeign.model.Person;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PersonClientFallback implements PersonClient {
    @Override
    public ResponseEntity<List<Person>> getAllPeople() {
        return ResponseEntity.ok(List.of());
    }

    @Override
    public ResponseEntity<Person> getPerson(Integer id) {
        return ResponseEntity.ok(new Person());
    }

    @Override
    public ResponseEntity<?> savePerson(Person person) {
        return ResponseEntity.badRequest().build();
    }

    @Override
    public ResponseEntity<?> deletePersonById(Integer id) {
        return ResponseEntity.badRequest().build();
    }
}
