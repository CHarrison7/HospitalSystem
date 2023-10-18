package com.ehealth.patientservice.controller;

import com.ehealth.patientservice.model.MedicationAdministration;
import com.ehealth.patientservice.model.Patient;
import com.ehealth.patientservice.service.MedicationAdministrationService;
import com.ehealth.patientservice.service.PatientService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/medicationadministration")
public class MedicationAdministrationController {

    private final MedicationAdministrationService medicationAdministrationService;
    private final PatientService patientService;

    public MedicationAdministrationController(MedicationAdministrationService medicationAdministrationService, PatientService patientService) {
        this.medicationAdministrationService = medicationAdministrationService;
        this.patientService = patientService;
    }

    @GetMapping(value ="/all")
    public List<MedicationAdministration> getAll() {
        return medicationAdministrationService.getAll();
    }

    @GetMapping(value = "/{medAdminId}")
    public MedicationAdministration getMedicationAdministration(@PathVariable Long medAdminId) {
        return medicationAdministrationService.getMedicationAdministration(medAdminId);
    }

    @PutMapping(value = "/{medAdminId}")
    public MedicationAdministration updateMedAdminById(@PathVariable Long medAdminId, @RequestBody MedicationAdministration medAdmin) {
        return medicationAdministrationService.updateMedicationAdministration(medAdminId, medAdmin);
    }

    @DeleteMapping(value =  "/{medAdminId}")
    public String deleteMedicationAdministration(@PathVariable Long medAdminId) {
        MedicationAdministration ma  = medicationAdministrationService.getMedicationAdministration(medAdminId);
        Long patientIdForMedAdmin = ma.getPatientId();
        patientService.deleteMedicationAdministrationFromPatient(medAdminId, patientIdForMedAdmin);
        medicationAdministrationService.deleteMedicationAdministration(medAdminId);

        return "MedicationAdministration with medAdminId " + medAdminId + " removed from patient with patientId " + patientIdForMedAdmin + " and deleted from MedicationAdministrationRepo!";
    }

}
