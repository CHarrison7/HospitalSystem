package com.ehealth.patientservice.service;

import com.ehealth.patientservice.data.MedicationAdministrationRepository;
import com.ehealth.patientservice.model.MedicationAdministration;
import com.ehealth.patientservice.model.Patient;
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

    public List<MedicationAdministration> addMedicationAdministrationToPatient(Patient patient, MedicationAdministration medicationAdministration) {
        medicationAdministrationRepo.save(medicationAdministration);
        return patient.addMedicationAdministrationToPatient(medicationAdministration);
    }

    public String deleteMedicationAdministration(Long medicationAdministrationId) {
        medicationAdministrationRepo.delete(medicationAdministrationRepo.findById(medicationAdministrationId).get());
        return "Medication Administration with medicationAdministrationId " + medicationAdministrationId + " deleted!";
    }

    public MedicationAdministration getMedicationAdministration(Long medicationAdministrationId) {
        return medicationAdministrationRepo.findById(medicationAdministrationId).get();
    }


}
