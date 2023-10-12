package HospitalSystem.PatientService.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.*;

@Entity @Getter @Setter @ToString @AllArgsConstructor @NoArgsConstructor
public class Patient {
    @Id
    @GeneratedValue
    private Long id;

    private String firstName;
    private String lastName;
    //private Date dob;

}
