package com.example.services;

import java.util.List;

import com.example.entities.InvoiceDetailsMaster;

public interface Invoice_dtlMasterService 
{

	List<InvoiceDetailsMaster> getAllInvoice_detailss();

	InvoiceDetailsMaster getInvoice_detailsById(int invoice_detailsId);

	InvoiceDetailsMaster addInvoice_details(InvoiceDetailsMaster invoice_details);

	InvoiceDetailsMaster updateInvoice_details(int invoice_detailsId, InvoiceDetailsMaster updatedInvoice_details);

	void deleteInvoice_details(int invoice_detailsId);
	
	List<InvoiceDetailsMaster> GetDetails (int invoiceID);

}
