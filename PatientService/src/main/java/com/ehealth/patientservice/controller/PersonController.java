package com.ehealth.patientservice.controller;

import com.ehealth.patientservice.model.Person;
import com.ehealth.patientservice.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/person")
public class PersonController {

    @Autowired
    PersonService personService;

    @GetMapping
    public Person getPerson(Long personId) {
        return personService.getPerson(personId);
    }

    @PostMapping
    public Person createPerson(Person person) {
        personService.createPerson(person);
        return person;
    }

    @GetMapping(value = "/all")
    public List<Person> getAllPersons() {
        return personService.getAllPersons();
    }

    @DeleteMapping
    public ResponseEntity deletePerson(Long personId) {
        return ResponseEntity.ok(personService.deletePerson(personId));
    }



}
