package com.example.services;

import java.util.List;

import com.example.entities.EmailMaster;
import com.example.entities.InvoiceMaster;

public interface InvoiceMasterService {

	InvoiceMaster saveInvoice(InvoiceMaster obj);
	List<InvoiceMaster> getAllInvoice();
	InvoiceMaster getInvoiceById(int id);
	void deleteInvoice(int id);
	InvoiceMaster updateInvoice(InvoiceMaster i, int id);
	List<InvoiceMaster> listAll();
	String sendMailWithAttachment(EmailMaster details);
}
