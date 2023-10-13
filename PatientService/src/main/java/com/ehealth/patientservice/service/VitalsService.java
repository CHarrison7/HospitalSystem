package com.ehealth.patientservice.service;

import com.ehealth.patientservice.data.VitalsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VitalsService {

    @Autowired
    private VitalsRepository vitalsRepo;


}
