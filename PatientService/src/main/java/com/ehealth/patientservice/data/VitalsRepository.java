package com.ehealth.patientservice.data;

import com.ehealth.patientservice.model.Vitals;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VitalsRepository extends JpaRepository<Vitals, Long> {
}
