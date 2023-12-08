package com.ehealth.billingservice.event;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ServiceAdministeredEvent {

    //private String serviceType;
    private float serviceCost;
    private String serviceDescription;
    //private Long serviceId;
    private Long patientId;



}
