package com.lazarev.cloudopenfeign.controller;

import com.lazarev.cloudopenfeign.feignclient.PersonClient;
import com.lazarev.cloudopenfeign.model.Person;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/feign/people")
@RequiredArgsConstructor
public class FeignClientController {
    private final PersonClient personClient;

    @GetMapping
    public ResponseEntity<List<Person>> getAllPeople(){
        return personClient.getAllPeople();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Person> getPerson(@PathVariable Integer id){
        return personClient.getPerson(id);
    }

    @PostMapping
    public ResponseEntity<?> savePerson(@RequestBody Person person){
        return personClient.savePerson(person);
    }

    @DeleteMapping("/{id}")
    public  ResponseEntity<?> deletePersonById(@PathVariable Integer id){
        return personClient.deletePersonById(id);
    }
}
