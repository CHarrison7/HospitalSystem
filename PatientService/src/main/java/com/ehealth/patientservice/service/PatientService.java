package com.ehealth.patientservice.service;

import com.ehealth.patientservice.data.MedicationAdministrationRepository;
import com.ehealth.patientservice.data.PatientRepository;
import com.ehealth.patientservice.data.PersonRepository;
import com.ehealth.patientservice.data.VitalsRepository;
import com.ehealth.patientservice.model.MedicationAdministration;
import com.ehealth.patientservice.model.Patient;
import com.ehealth.patientservice.model.Vitals;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatientService {

    @Autowired
    private static PatientRepository patientRepo;
    @Autowired
    private static PersonRepository personRepo;
    @Autowired
    private static PersonService personService;
    @Autowired
    private static MedicationAdministrationRepository medicationAdministrationRepository;
    @Autowired
    private static MedicationAdministrationService medicationAdministationService;
    @Autowired
    private static VitalsService viralsService;
    @Autowired
    private static VitalsRepository vitalsRepository;

    public PatientService(PatientRepository patientRepo) { this.patientRepo = patientRepo; }

    public List<Patient> getAllPatients() {
        return patientRepo.findAll();
    }

    public Patient createPatient(Patient patient)  {
        patientRepo.save(patient);
        return patient;
    }

    public Patient updatePatient(Long patientId, Patient patient) {
        Patient old = patientRepo.findById(patientId).get();
        old = patient;
        patientRepo.save(old);
        return old;
    }

    public String deletePatient(Long patientId) {
        patientRepo.delete(patientRepo.findById(patientId).get());
        return "Patient with Id " + patientId + " deleted!";
    }

    public List<Vitals> addVitalsToPatient(Long patientId, Vitals vitals) {
        Patient patient = patientRepo.findById(patientId).get();
        List<Vitals> ret = patient.addVitalsToPatient(vitals);
        patientRepo.save(patient);
        return ret;
    }

    public Patient getPatientById(Long patientId) {
        return patientRepo.findById(patientId).get();
    }

    public List<Vitals> getPatientVitals(Long patientId) {
        return patientRepo.findById(patientId).get().getVitalsList();
    }

    public List<MedicationAdministration> getPatientMedicationAdministration(Long patientId) {
        return patientRepo.findById(patientId).get().getMedicationAdministrationList();
    }

    public String deleteMedicationAdministrationFromPatient(Long medicationAdministrationId, Long patientId) {
        MedicationAdministration medAdmin = medicationAdministationService.getMedicationAdministration(medicationAdministrationId);
        Patient patient = patientRepo.findById(patientId).get();
        patient.getMedicationAdministrationList().remove(medAdmin);
        patientRepo.save(patient);
        medicationAdministrationRepository.delete(medAdmin);
        return "Medication Administration with medicationAdministrationId " + medicationAdministrationId + " deleted from Patient with patientId " + patientId + "!";
    }

    public String deleteVitalsFromPatient(Long vitalsId, Long patientId) {
        Vitals vitals = viralsService.getVitals(vitalsId).get();
        Patient patient = patientRepo.findById(patientId).get();
        patient.getVitalsList().remove(vitals);
        patientRepo.save(patient);
        vitalsRepository.delete(vitals);
        return "Vitals with vitalsId " + vitalsId + " deleted from Patient with patientId " + patientId + "!";
    }

}
