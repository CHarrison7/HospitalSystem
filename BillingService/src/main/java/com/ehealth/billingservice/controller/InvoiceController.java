package com.ehealth.billingservice.controller;

import com.ehealth.billingservice.model.Invoice;
import com.ehealth.billingservice.service.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/invoices")
public class InvoiceController {

    @Autowired
    private InvoiceService invoiceService;

    public InvoiceController(InvoiceService invoiceService) { this.invoiceService = invoiceService; }

    @GetMapping(value = "/all")
    public List<Invoice> getAllInvoices() { return invoiceService.getAll(); }

    /*
    @GetMapping(value  = "/{invoiceId}")
    public Invoice getPatientInvoiceByInvoiceId(@PathVariable Long invoiceId) {
        return invoiceService.getInvoice(invoiceId).get();
    }
    */

    @GetMapping(value = "/{patientId}")
    public Invoice getPatientInvoiceByPatientId(@PathVariable Long patientId) {
        return invoiceService.getInvoiceByPatientId(patientId);
    }

    @PostMapping(value = "/create/{patientId}")
    public Invoice createInvoiceForPatient(@PathVariable Long patientId) {
        return invoiceService.createInvoice(patientId);
    }

    @PostMapping(value = "/update/addservice/{patientId}")
    public ResponseEntity<Invoice> updateInvoiceToAddServiceAndCost(@PathVariable Long patientId) {
        Long invoiceId = invoiceService.getInvoiceByPatientId(patientId).getId();
        Invoice invoice = invoiceService.updateInvoiceServicesListAndBalance(invoiceId, "Dummy Service", 100);
        //return "Invoice with Id: " +  invoiceId + " updated!";
        return ResponseEntity.ok(invoice);
    }

}
