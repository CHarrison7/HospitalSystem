package com.ehealth.patientservice.model;

import jakarta.persistence.*;
import lombok.*;

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

    @ElementCollection(targetClass = String.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "knownConditions", joinColumns = @JoinColumn(name = "patient_id"))
    @Column(name = "condition", nullable = false)
    private List<String> knownConditions;

    @ElementCollection(targetClass = String.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "prescribedMedications", joinColumns = @JoinColumn(name = "patient_id"))
    @Column(name = "medication", nullable = false)
    private List<String> prescribedMedications;

    @ElementCollection(targetClass = String.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "regularMedications", joinColumns = @JoinColumn(name = "patient_id"))
    @Column(name = "regularMedication", nullable = false)
    private List<String> regularMedications;

    @ElementCollection(targetClass = String.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "allergies", joinColumns = @JoinColumn(name = "patient_id"))
    @Column(name = "allergy", nullable = false)
    private List<String> allergies;

    @ElementCollection(targetClass = String.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "pastProcedures", joinColumns = @JoinColumn(name = "patient_id"))
    @Column(name = "procedure", nullable = false)
    private List<String> pastProcedures;

    @ElementCollection(targetClass = String.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "diagnosis", joinColumns = @JoinColumn(name = "patient_id"))
    @Column(name = "diagnosis", nullable = false)
    private List<String> diagnosis;

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
