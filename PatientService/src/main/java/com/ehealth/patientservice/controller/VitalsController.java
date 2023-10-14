package com.ehealth.patientservice.controller;

import com.ehealth.patientservice.model.Vitals;
import com.ehealth.patientservice.service.VitalsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/api/vitals")
public class VitalsController {

    @Autowired
    private VitalsService vitalsService;

    @GetMapping
    public Vitals getVitals(@PathVariable Long vitalsId) {
        return vitalsService.getVitals(vitalsId).get();
    }

    @GetMapping("/all")
    public List<Vitals> getVitals() {
        return vitalsService.getAll();
    }
}
