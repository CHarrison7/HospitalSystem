package com.ehealth.patientservice.controller;

import com.ehealth.patientservice.model.Vitals;
import com.ehealth.patientservice.service.PatientService;
import com.ehealth.patientservice.service.VitalsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/vitals")
public class VitalsController {

    @Autowired
    private VitalsService vitalsService;
    @Autowired
    private PatientService patientService;

    @GetMapping(value = "/{vitalsId}")
    public Vitals getVitals(@PathVariable Long vitalsId) {
        return vitalsService.getVitals(vitalsId).get();
    }

    @GetMapping("/all")
    public List<Vitals> getAllVitals() {
        return vitalsService.getAll();
    }

    @PutMapping(value = "/{vitalsId}")
    public Vitals updateVitalsById(@PathVariable Long vitalsId, @RequestBody Vitals vitals) {
        return vitalsService.updateVitals(vitalsId, vitals);
    }

    @DeleteMapping(value = "/{vitalsId}")
    public String deleteVitals(@PathVariable Long vitalsId) {

        Vitals v =  vitalsService.getVitals(vitalsId).get();
        Long patientIdForVitals = v.getPatientId();
        patientService.deleteVitalsFromPatient(vitalsId, patientIdForVitals);
        vitalsService.deleteVitals(vitalsId);

        return "Vitals with vitalsId " + vitalsId + " removed from patient with patientId " + patientIdForVitals + " and deleted from VitalsRepo!";
    }
}
