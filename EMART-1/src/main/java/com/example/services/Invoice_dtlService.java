package com.example.services;

import com.example.entities.Invoice_dtl;

public interface Invoice_dtlService {
	
	Invoice_dtl getInvoice_detailsById(int invdtl_id);
	Invoice_dtl addInvoice_details(Invoice_dtl invoice_details);
	Invoice_dtl getInvoice_detailsByInvoiceId(int inv_dtl);
}