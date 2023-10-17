package com.ehealth.patientservice.controller;

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

    @PostMapping
    public ResponseEntity<Patient> createPatient(@RequestBody Patient patient) {
        return ResponseEntity.ok(patientService.createPatient(patient));
    }

    @PutMapping
    public ResponseEntity<Patient> updatePatient(@RequestBody Patient patient) {
        return ResponseEntity.ok(patientService.updatePatient(patient));
    }

    @DeleteMapping
    public ResponseEntity deletePatient(@PathVariable Long patientId) {
        return ResponseEntity.ok(patientService.deletePatient(patientId));
    }

    @PostMapping(value = "/addvitals")
    public ResponseEntity addVitalsToPatient(@PathVariable Long patientId, @RequestBody Vitals vitals) {
        return ResponseEntity.ok(patientService.addVitalsToPatient(patientId, vitals));
    }

    @PostMapping(value = "/addmedicationadministration")
    public ResponseEntity addMedicationAdministrationToPatient(@PathVariable Long patientId, @RequestBody Vitals vitals) {
        return ResponseEntity.ok(patientService.addVitalsToPatient(patientId, vitals));
    }


}
