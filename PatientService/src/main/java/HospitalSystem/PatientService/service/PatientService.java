package HospitalSystem.PatientService.service;

import HospitalSystem.PatientService.data.PatientRepository;
import HospitalSystem.PatientService.model.Patient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatientService {

    @Autowired
    public static PatientRepository patientRepo;

    public PatientService(PatientRepository patientRepo) { this.patientRepo = patientRepo; }

    public List<Patient> getAllPatients() {
        return patientRepo.findAll();
    }

}
