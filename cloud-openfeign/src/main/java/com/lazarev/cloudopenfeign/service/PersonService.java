package com.lazarev.cloudopenfeign.service;

import com.lazarev.cloudopenfeign.exception.NoSuchPersonException;
import com.lazarev.cloudopenfeign.model.Person;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class PersonService {
    private static final List<Person> PEOPLE = new ArrayList<>(List.of(
            new Person(1, "Mike", 25),
            new Person(2, "Bob", 20),
            new Person(3, "Kate", 22),
            new Person(4, "John", 18)));

    public List<Person> getAllPeople(){
        return PEOPLE;
    }

    public Person getPersonById(Integer id){
        return PEOPLE.stream()
                .filter(p -> p.getId().equals(id))
                .findFirst()
                .orElseThrow(()->new NoSuchPersonException("Person with id = '%d' not found".formatted(id)));
    }

    public void savePerson(Person person){
        Integer lastId = PEOPLE.get(PEOPLE.size()-1).getId();
        person.setId(lastId + 1);
        PEOPLE.add(person);
    }

    public boolean delete(Integer id){
        Person person = PEOPLE.stream()
                .filter(p -> p.getId().equals(id))
                .findFirst()
                .orElseThrow(()->new NoSuchPersonException("Person with id = '%d' not found".formatted(id)));

        return PEOPLE.remove(person);
    }
}
