package com.ehealth.patientservice.service;

import com.ehealth.patientservice.data.VitalsRepository;
import com.ehealth.patientservice.model.MedicationAdministration;
import com.ehealth.patientservice.model.Vitals;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VitalsService {

    @Autowired
    private VitalsRepository vitalsRepo;

    public Vitals createVitals(Vitals vitals) {
        vitalsRepo.save(vitals);
        return vitals;
    }

    public List<Vitals> getAll() {
        return vitalsRepo.findAll();
    }

    public Optional<Vitals> getVitals(Long vitalsId)  {
        return vitalsRepo.findById(vitalsId);
    }

    public Vitals updateVitals(Vitals vitals) {
        return vitalsRepo.save(vitals);
    }

    public String deleteVitals(Long vitalsId) {
        vitalsRepo.delete(vitalsRepo.findById(vitalsId).get());
        return "Vitals set with vitalsId " + vitalsId + " deleted!";
    }

}
