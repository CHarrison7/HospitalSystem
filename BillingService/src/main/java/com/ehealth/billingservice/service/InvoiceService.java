package com.ehealth.billingservice.service;

import com.ehealth.billingservice.data.InvoiceRepository;
import com.ehealth.billingservice.model.Invoice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InvoiceService {

    @Autowired
    private InvoiceRepository invoiceRepo;

    public InvoiceService(InvoiceRepository invoiceRepo) { this.invoiceRepo = invoiceRepo; }

public Invoice createInvoice(Long patientId) {
        Invoice i = new Invoice();
        i.setPatientId(patientId);
        i.setPaid(false);
        i.setBalance(i.getBalance() + 5000); // add base fee of $5000
        invoiceRepo.save(i);
        return i;
}

public List<Invoice> getAll() {
        return invoiceRepo.findAll();
}

public Optional<Invoice> getInvoice(Long invoiceId) {
        return invoiceRepo.findById(invoiceId);
}

public Invoice updateInvoiceServicesListAndBalance(Long invoiceId, String s, float cost) {
        Invoice i = invoiceRepo.findById(invoiceId).get();
        i.addService(s);
        i.addToBalance(cost);
        invoiceRepo.save(i);
        return i;
}

public Invoice getInvoiceByPatientId(Long patientId) {
        List<Invoice> invoiceList = invoiceRepo.findAll();

        for(int i = 0; i < invoiceList.size(); i++) {
            if(invoiceList.get(i).getPatientId() == patientId) {
                return invoiceList.get(i);
            }
        }
        return null;
}



}
