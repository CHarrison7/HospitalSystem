package com.ehealth.patientservice.service;

import com.ehealth.patientservice.data.MedicationAdministrationRepository;
import com.ehealth.patientservice.data.PatientRepository;
import com.ehealth.patientservice.data.VitalsRepository;
import com.ehealth.patientservice.event.ServiceAdministeredEvent;
import com.ehealth.patientservice.model.Invoice;
import com.ehealth.patientservice.model.MedicationAdministration;
import com.ehealth.patientservice.model.Patient;
import com.ehealth.patientservice.model.Vitals;
import com.ehealth.patientservice.service.client.InvoiceFeignClient;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeoutException;

@Service
public class PatientService {

    @Autowired
    private static PatientRepository patientRepo;

    @Autowired
    private static MedicationAdministrationRepository medicationAdministrationRepository;
    @Autowired
    private static MedicationAdministrationService medicationAdministationService;
    @Autowired
    private static VitalsService vitalsService;
    @Autowired
    private static VitalsRepository vitalsRepository;

    @Autowired
    private KafkaTemplate<String, ServiceAdministeredEvent> ServiceAdministeredKafkaTemplate;

    @Autowired
    InvoiceFeignClient invoiceFeignClient;

    private static final Logger logger = LoggerFactory.getLogger(PatientService.class);


    public PatientService(PatientRepository patientRepo, MedicationAdministrationService medicationAdministrationService, VitalsService vitalsService,
                          MedicationAdministrationRepository medicationAdministrationRepository, VitalsRepository vitalsRepository) {
        this.patientRepo = patientRepo;
        this.medicationAdministationService = medicationAdministrationService;
        this.vitalsService = vitalsService;
        this.medicationAdministrationRepository = medicationAdministrationRepository;
        this.vitalsRepository =  vitalsRepository;
    }



    public List<Patient> getAllPatients() {
        return patientRepo.findAll();
    }


    public Patient createPatient(Patient patient)  {
        patientRepo.save(patient);
        Invoice invoice = invoiceFeignClient.createInvoiceWithPatientId(patient.getId());
        System.out.println(invoice.toString());
        return patient;
    }

    public Patient updatePatient(Long patientId, Patient patient) {
        Patient old = patientRepo.findById(patientId).get();

        // don't know how to quickly/easily update any changed attributes, so i will manually do it for all attributes
        old.setAllergies(patient.getAllergies());
        old.setDob(patient.getDob());
        old.setDiagnosis(patient.getDiagnosis());
        old.setLastName(patient.getLastName());
        old.setFirstName(patient.getFirstName());
        old.setPastProcedures(patient.getPastProcedures());
        old.setPhoneNumber(patient.getPhoneNumber());
        old.setKnownConditions(patient.getKnownConditions());
        old.setPrescribedMedications(patient.getPrescribedMedications());
        old.setMedicationAdministrationList(patient.getMedicationAdministrationList());
        old.setRegularMedications(patient.getRegularMedications());
        old.setVitalsList(patient.getVitalsList());

        patientRepo.save(old);
        return old;
    }

    public String deletePatient(Long patientId) {
        patientRepo.delete(patientRepo.findById(patientId).get());
        return "Patient with Id " + patientId + " deleted!";
    }

    public List<Vitals> addVitalsToPatient(Long patientId, Vitals vitals) {
        Patient patient = patientRepo.findById(patientId).get();
        vitals.setTimeTaken(Date.from(Instant.now()));
        vitals.setPatientId(patientId);
        vitalsRepository.save(vitals);
        List<Vitals> ret = patient.addVitalsToPatient(vitals);
        patientRepo.save(patient);
        return ret;
    }

    public List<MedicationAdministration> addMedicationAdministrationToPatient(Long patientId, MedicationAdministration medAdmin){
        Patient patient = patientRepo.findById(patientId).get();
        medAdmin.setAdministrationTime(Date.from(Instant.now()));
        medAdmin.setPatientId(patientId);
        medicationAdministrationRepository.save(medAdmin);
        List<MedicationAdministration> ret  = patient.addMedicationAdministrationToPatient(medAdmin);
        patientRepo.save(patient);

        //Invoice invoice = invoiceFeignClient.updateInvoiceToAddServiceAndCost(patient.getId());

        ServiceAdministeredEvent serviceAdministeredEvent = new ServiceAdministeredEvent();
        serviceAdministeredEvent.setPatientId(patientId);
        serviceAdministeredEvent.setServiceCost(100);
        serviceAdministeredEvent.setServiceDescription("Medicine: " + medAdmin.getMedication() + " ; dosage: " + medAdmin.getDosage());

        ServiceAdministeredKafkaTemplate.send("serviceAdministeredTopic", serviceAdministeredEvent);


        //invoiceFeignClient.updateInvoiceToAddServiceAndCost(patient.getId());

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
        return "Medication Administration with medicationAdministrationId " + medicationAdministrationId + " deleted from Patient with patientId " + patientId + "!";
    }

    public String deleteVitalsFromPatient(Long vitalsId, Long patientId) {
        Vitals vitals = vitalsService.getVitals(vitalsId).get();
        Patient patient = patientRepo.findById(patientId).get();
        patient.getVitalsList().remove(vitals);
        patientRepo.save(patient);
        return "Vitals with vitalsId " + vitalsId + " deleted from Patient with patientId " + patientId + "!";
    }




    @CircuitBreaker(name = "getAllPatientsTestCircuitBreaker", fallbackMethod = "getAllPatientsTestCircuitBreakerFALLBACK")
    public List<Patient> getAllPatientsTestCircuitBreaker() throws TimeoutException {
        randomlyRunLong();
        return patientRepo.findAll();
    }

    private List<Patient> getAllPatientsTestCircuitBreakerFALLBACK(Throwable t) {
        System.out.println("IN FALLBACK");
        Patient p = new Patient();
        p.setId(0L);
        p.setFirstName("Service");
        p.setLastName("Unavailable");
        List<Patient> lp = new ArrayList<>();
        lp.add(p);
        return lp;
    }


    private void randomlyRunLong(){
        System.out.println("RANDOMLY RUNNING LONG");
        Random rand = new Random();
        int randomNum = rand.nextInt(3);
        if (randomNum % 2 == 0 ) sleep();
    }
    private void sleep(){
        try {
            Thread.sleep(15000);
        } catch (InterruptedException e) {
            logger.error(e.getMessage());
        }
    }

}
