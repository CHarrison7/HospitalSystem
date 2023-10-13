package com.ehealth.patientservice.service;

import com.ehealth.patientservice.data.MedicationAdministrationRepository;
import com.ehealth.patientservice.model.MedicationAdministration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MedicationAdministrationService {

    @Autowired
    private MedicationAdministrationRepository medicationAdministrationRepo;

    public List<MedicationAdministration> getAll() {
        return medicationAdministrationRepo.findAll();
    }
}
