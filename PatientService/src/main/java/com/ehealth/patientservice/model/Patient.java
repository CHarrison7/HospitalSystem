package com.ehealth.patientservice.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity @Data @AllArgsConstructor @NoArgsConstructor
public class Patient extends Person {
    @Id
    @GeneratedValue
    private Long id;
    private Date dob;

    @Transient
    private List<String> knownConditions;

    @Transient
    private List<String> medications;

    @Transient
    private List<String> regularMedications;

    @Transient
    private List<String> allergies;

    @Transient
    private List<String> pastProcedures;

    @Transient
    private List<String> diagnosis;

    @OneToOne
    private Person emergencyContact;

    @OneToMany
    private List<Vitals> vitalsList;

    @OneToMany
    private List<MedicationAdministration> medicationAdministrationList;





}
