package com.ehealth.patientservice.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity @Data @AllArgsConstructor @NoArgsConstructor
public class Vitals {

    @Id @GeneratedValue
    private Long id;

    private Long patientId;

    private Date timeTaken; // date and time of vitals recording
    private String bloodPressure; // string in form of systolic/diastolic (e.g. "110/80")
    private int heartRate; // integer for beats per minute
    private int oxygenSaturation; // integer for percentage 0-100
    private int painLevel;  // integer based on numerical scale 0-10 and the Wong-Baker Faces Pain Scale



}
