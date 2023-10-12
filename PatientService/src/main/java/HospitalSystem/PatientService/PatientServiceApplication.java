package HospitalSystem.PatientService;

import HospitalSystem.PatientService.data.PatientRepository;
import HospitalSystem.PatientService.model.Patient;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class PatientServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(PatientServiceApplication.class, args);
	}

	@Bean
	CommandLineRunner commandLineRunner(PatientRepository patientRepo) {
		Patient p = new Patient();
		p.setFirstName("Caleb");
		p.setLastName("Harrison");
		return args -> patientRepo.save(p);
	}

}
