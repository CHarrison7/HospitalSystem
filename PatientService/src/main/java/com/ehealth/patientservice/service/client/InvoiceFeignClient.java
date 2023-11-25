package com.ehealth.patientservice.service.client;

import com.ehealth.patientservice.model.Invoice;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@FeignClient("billingservice")
public interface InvoiceFeignClient {
    @RequestMapping(
            method= RequestMethod.GET,
            value="/api/invoices/{patientId}",
            consumes="application/json")
    Invoice getInvoiceByPatientId(@PathVariable("patientId") Long patientId);

    @RequestMapping(
            method= RequestMethod.POST,
            value="/api/invoices/create/{patientId}",
            consumes="application/json")
    Invoice createInvoiceWithPatientId(@PathVariable("patientId") Long patientId);

    @RequestMapping(
            method= RequestMethod.POST,
            value="/api/invoices/update/addservice/{patientId}",
            consumes="application/json")//, produces = "application/json")
    Invoice updateInvoiceToAddServiceAndCost(@PathVariable("patientId") Long patientId);

}

