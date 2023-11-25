package com.ehealth.billingservice.data;

import com.ehealth.billingservice.model.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InvoiceRepository extends JpaRepository<Invoice, Long> {

}
