package com.ehealth.patientservice.data;

import com.ehealth.patientservice.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, Long> {
}
