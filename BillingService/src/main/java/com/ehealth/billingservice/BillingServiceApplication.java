package com.ehealth.billingservice;

import com.ehealth.billingservice.event.ServiceAdministeredEvent;
import com.ehealth.billingservice.service.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.kafka.annotation.KafkaListener;


@SpringBootApplication
@RefreshScope
//@EnableFeignClients
public class BillingServiceApplication {

    @Autowired
    InvoiceService invoiceService;

    public static void main(String[] args) {
        SpringApplication.run(BillingServiceApplication.class, args);
    }

    @KafkaListener(topics = "serviceAdministeredTopic")
    public void ServiceAdministeredEventHandler(ServiceAdministeredEvent serviceAdministeredEvent) {
        System.out.println("Message received: " + serviceAdministeredEvent.toString());
        invoiceService.updateInvoiceFromServiceAdministeredEvent(serviceAdministeredEvent);
    }

}
