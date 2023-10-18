package com.ehealth.patientservice.service;

import com.ehealth.patientservice.data.MedicationAdministrationRepository;
import com.ehealth.patientservice.data.PatientRepository;
import com.ehealth.patientservice.model.MedicationAdministration;
import com.ehealth.patientservice.model.Patient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MedicationAdministrationService {

    @Autowired
    private MedicationAdministrationRepository medicationAdministrationRepo;

    public MedicationAdministrationService(MedicationAdministrationRepository medicationAdministrationRepo) { this.medicationAdministrationRepo = medicationAdministrationRepo; }

    public List<MedicationAdministration> getAll() {
        return medicationAdministrationRepo.findAll();
    }

    public List<MedicationAdministration> addMedicationAdministrationToPatient(Patient patient, MedicationAdministration medicationAdministration) {
        medicationAdministrationRepo.save(medicationAdministration);
        return patient.addMedicationAdministrationToPatient(medicationAdministration);
    }

    public MedicationAdministration updateMedicationAdministration(Long medicationAdministrationId, MedicationAdministration medicationAdministration) {
        MedicationAdministration ma = medicationAdministrationRepo.findById(medicationAdministrationId).get();
        ma.setMedication(medicationAdministration.getMedication());
        ma.setDosage(medicationAdministration.getDosage());

        return medicationAdministrationRepo.save(ma);
    }

    public String deleteMedicationAdministration(Long medicationAdministrationId) {
        medicationAdministrationRepo.delete(medicationAdministrationRepo.findById(medicationAdministrationId).get());
        return "Medication Administration with medicationAdministrationId " + medicationAdministrationId + " deleted!";
    }

    public MedicationAdministration getMedicationAdministration(Long medicationAdministrationId) {
        return medicationAdministrationRepo.findById(medicationAdministrationId).get();
    }


}
