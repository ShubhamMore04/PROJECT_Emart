package com.example.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entities.Invoice_dtl;
import com.example.repositories.Invoice_dtlRepository;

@Service
public class Invoice_dtlServiceImpl implements Invoice_dtlService{
	
	@Autowired
	private Invoice_dtlRepository invoice_dtlrepo;
	@Override
	public Invoice_dtl getInvoice_detailsById(int invdtl_id) {
		return invoice_dtlrepo.findById(invdtl_id).get();
	}

	@Override
	public Invoice_dtl addInvoice_details(Invoice_dtl invoice_details) {
		return invoice_dtlrepo.save(invoice_details);
	}

	@Override
	public Invoice_dtl getInvoice_detailsByInvoiceId(int inv_id) {
		return invoice_dtlrepo.getInvoice_dtlIdByInvoiceId(inv_id);
	}

}