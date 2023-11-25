package com.ehealth.patientservice.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.hateoas.RepresentationModel;

import java.util.List;

@Getter @Setter @ToString
public class Invoice extends RepresentationModel<Invoice> {

    Long id;

    long patientId;

    float balance;

    boolean isPaid;

    List<String> servicesList;

}
