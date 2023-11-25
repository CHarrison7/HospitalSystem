package com.ehealth.billingservice.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Invoice {

    @Id
    @GeneratedValue
    Long id;

    long patientId;
    float balance;
    boolean isPaid;

    @ElementCollection(targetClass = String.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "services", joinColumns = @JoinColumn(name = "invoice_id"))
    @Column(name = "service", nullable = false)
    List<String> servicesList;

    public String addService(String s) {
        servicesList.add(s);
        return "Service added: " + s;
    }

    public void addToBalance(float x) {
        balance += x;
    }

}
