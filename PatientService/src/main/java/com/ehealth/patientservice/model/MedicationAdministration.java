package com.ehealth.patientservice.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity @Data @AllArgsConstructor @NoArgsConstructor
public class MedicationAdministration {

    @Id @GeneratedValue
    private Long id;

    private Long patientId;
    private String medication;
    private String dosage;
    private Date administrationTime;
}
