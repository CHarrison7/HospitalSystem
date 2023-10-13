package com.ehealth.patientservice.data;

import com.ehealth.patientservice.model.MedicationAdministration;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MedicationAdministrationRepository extends JpaRepository<MedicationAdministration, Long> {
}
