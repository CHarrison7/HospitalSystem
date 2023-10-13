package com.ehealth.patientservice.service;

import com.ehealth.patientservice.data.PersonRepository;
import com.ehealth.patientservice.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonService {

    @Autowired
    private PersonRepository personRepository;

    public Person createPerson(Person person) {
        personRepository.save(person);
        return person;
    }
}
