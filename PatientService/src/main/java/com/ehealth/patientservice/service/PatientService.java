package com.ehealth.patientservice.service;

import com.ehealth.patientservice.data.PatientRepository;
import com.ehealth.patientservice.data.PersonRepository;
import com.ehealth.patientservice.model.Patient;
import com.ehealth.patientservice.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatientService {

    @Autowired
    public static PatientRepository patientRepo;
    public static PersonRepository personRepo;
    public static PersonService personService;

    public PatientService(PatientRepository patientRepo) { this.patientRepo = patientRepo; }

    public List<Patient> getAllPatients() {
        return patientRepo.findAll();
    }

    public Patient createPatient(Patient patient)  {
        Person person = new Person();
        person.setFirstName(patient.getFirstName());
        person.setLastName(patient.getLastName());
        person.setPhoneNumber(patient.getPhoneNumber());
        personService.createPerson(person);
        patientRepo.save(patient);

        person.setPatientId(patient.getPatientId());
        person.setIsPatient(true);

        personRepo.save(person);

        return patient;

    }

}
