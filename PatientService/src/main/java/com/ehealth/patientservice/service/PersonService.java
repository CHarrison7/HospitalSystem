package com.ehealth.patientservice.service;

import com.ehealth.patientservice.data.PersonRepository;
import com.ehealth.patientservice.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonService {

    @Autowired
    private PersonRepository personRepo;

    public Person createPerson(Person person) {
        personRepo.save(person);
        return person;
    }

    public Person getPerson(Long personId) {
        return personRepo.findById(personId).get();
    }

    public String deletePerson(Long personId) {
        personRepo.delete(personRepo.findById(personId).get());
        return "Person deleted!";
    }

    public List<Person> getAllPersons() {
        return personRepo.findAll();
    }
}
