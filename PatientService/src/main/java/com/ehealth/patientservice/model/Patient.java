package com.ehealth.patientservice.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity @Data @AllArgsConstructor @NoArgsConstructor
public class Patient {
    @Id
    @GeneratedValue
    private Long id;
    private String firstName;
    private String lastName;
    private String phoneNumber;
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

    //@OneToOne(cascade = CascadeType.PERSIST)
    //private Person emergencyContact;

    //private Long emergencyContactPersonId;

    @OneToMany
    private List<Vitals> vitalsList;

    @OneToMany
    private List<MedicationAdministration> medicationAdministrationList;


    public List<Vitals> addVitalsToPatient(Vitals vitals) {
        vitalsList.add(vitals);
        return vitalsList;
    }

    public List<MedicationAdministration> addMedicationAdministrationToPatient(MedicationAdministration medicationAdministration) {
        medicationAdministrationList.add(medicationAdministration);
        return medicationAdministrationList;
    }




}
