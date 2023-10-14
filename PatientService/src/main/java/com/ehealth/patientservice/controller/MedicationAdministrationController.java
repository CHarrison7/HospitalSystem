package com.ehealth.patientservice.controller;

import com.ehealth.patientservice.model.MedicationAdministration;
import com.ehealth.patientservice.model.Patient;
import com.ehealth.patientservice.service.MedicationAdministrationService;
import com.ehealth.patientservice.service.PatientService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/api/medicationadministration")
public class MedicationAdministrationController {

    private final MedicationAdministrationService medicationAdministrationService;

    public MedicationAdministrationController(MedicationAdministrationService medicationAdministrationService) {
        this.medicationAdministrationService = medicationAdministrationService;
    }

    @GetMapping(value ="/all")
    public List<MedicationAdministration> getAll() {
        return medicationAdministrationService.getAll();
    }

    @GetMapping
    public MedicationAdministration getMedicationAdministration(Long medAdminId) {
        return medicationAdministrationService.getMedicationAdministration(medAdminId);
    }

}
