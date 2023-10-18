package com.ehealth.patientservice.service;

import com.ehealth.patientservice.data.VitalsRepository;
import com.ehealth.patientservice.model.Vitals;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VitalsService {

    @Autowired
    private VitalsRepository vitalsRepo;

    public VitalsService(VitalsRepository vitalsRepo) { this.vitalsRepo = vitalsRepo; }

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

    public Vitals updateVitals(Long vitalsId, Vitals vitals) {
        Vitals v = vitalsRepo.findById(vitalsId).get();
        v.setBloodPressure(vitals.getBloodPressure());
        v.setHeartRate(vitals.getHeartRate());
        v.setPainLevel(vitals.getPainLevel());
        v.setOxygenSaturation(vitals.getOxygenSaturation());

        return vitalsRepo.save(v);
    }

    public String deleteVitals(Long vitalsId) {
        vitalsRepo.delete(vitalsRepo.findById(vitalsId).get());
        return "Vitals set with vitalsId " + vitalsId + " deleted!";
    }

}
