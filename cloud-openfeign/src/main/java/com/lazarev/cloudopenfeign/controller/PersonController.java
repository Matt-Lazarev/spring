package com.lazarev.cloudopenfeign.controller;

import com.lazarev.cloudopenfeign.model.Person;
import com.lazarev.cloudopenfeign.service.PersonService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/people")
@RequiredArgsConstructor
public class PersonController {
    private final PersonService personService;

    @GetMapping
    public ResponseEntity<List<Person>> getAllPeople(){
        List<Person> people = personService.getAllPeople();
        if(people.size() != 0){
            return ResponseEntity.ok(people);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Person> getPerson(@PathVariable Integer id){
        return ResponseEntity.ok(personService.getPersonById(id));
    }

    @PostMapping
    public ResponseEntity<?> savePerson(@RequestBody Person person){
        personService.savePerson(person);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public  ResponseEntity<?> deletePersonById(@PathVariable Integer id){
        boolean deleted = personService.delete(id);
        if(deleted){
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}
