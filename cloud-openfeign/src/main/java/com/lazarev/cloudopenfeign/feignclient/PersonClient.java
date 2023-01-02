package com.lazarev.cloudopenfeign.feignclient;

import com.lazarev.cloudopenfeign.config.feign.PersonClientConfig;
import com.lazarev.cloudopenfeign.config.feign.PersonClientFallback;
import com.lazarev.cloudopenfeign.model.Person;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(name = "person-service",
        url = "http://localhost:8080",
        path = "/api/people",
        configuration = PersonClientConfig.class,
        fallback = PersonClientFallback.class)
public interface PersonClient {
    @GetMapping
    ResponseEntity<List<Person>> getAllPeople();

    @GetMapping("/{id}")
    ResponseEntity<Person> getPerson(@PathVariable("id") Integer id);

    @PostMapping
    ResponseEntity<?> savePerson(@RequestBody Person person);

    @DeleteMapping("/{id}")
    ResponseEntity<?> deletePersonById(@PathVariable("id") Integer id);
}
