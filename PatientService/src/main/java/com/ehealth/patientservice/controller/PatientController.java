package com.ehealth.patientservice.controller;

import com.ehealth.patientservice.model.MedicationAdministration;
import com.ehealth.patientservice.model.Patient;
import com.ehealth.patientservice.model.Vitals;
import com.ehealth.patientservice.service.PatientService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/patients")
public class PatientController {

    private final PatientService patientService;

    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @GetMapping(value = "/all")
    public List<Patient> getAllPatients() {
        return patientService.getAllPatients();
    }

    @GetMapping(value = "/{patientId}")
    public Patient getPatient(@PathVariable Long patientId) {
        return patientService.getPatientById(patientId);
    }

    @PostMapping
    public ResponseEntity<Patient> createPatient(@RequestBody Patient patient) {
       // this also calls BillingService's "createInvoiceForPatient" controller method too
        return ResponseEntity.ok(patientService.createPatient(patient));
    }

    @PutMapping(value = "/{patientId}")
    public ResponseEntity<Patient> updatePatient(@PathVariable Long patientId, @RequestBody Patient patient) {
        return ResponseEntity.ok(patientService.updatePatient(patientId, patient));
    }

    @DeleteMapping(value = "/{patientId}")
    public ResponseEntity deletePatient(@PathVariable Long patientId) {
        return ResponseEntity.ok(patientService.deletePatient(patientId));
    }

    @PostMapping(value = "/addvitals/{patientId}")
    public ResponseEntity addVitalsToPatient(@PathVariable Long patientId, @RequestBody Vitals vitals) {
        return ResponseEntity.ok(patientService.addVitalsToPatient(patientId, vitals));
    }

    @PostMapping(value = "/addmedicationadministration/{patientId}")
    public ResponseEntity addMedicationAdministrationToPatient(@PathVariable Long patientId, @RequestBody MedicationAdministration medAdmin) {
        // this also calls the BillingService's updateInvoiceToAddServiceAndCost controller method
        return ResponseEntity.ok(patientService.addMedicationAdministrationToPatient(patientId, medAdmin));
    }


}
