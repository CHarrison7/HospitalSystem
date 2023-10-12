package HospitalSystem.PatientService.data;

import HospitalSystem.PatientService.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientRepository extends JpaRepository<Patient, Long> {
}
