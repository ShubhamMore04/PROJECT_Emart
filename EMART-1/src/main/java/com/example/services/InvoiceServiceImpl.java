package com.example.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entities.Invoice;
import com.example.repositories.InvoiceRepository;

@Service
public class InvoiceServiceImpl implements InvoiceService {

	@Autowired
	private InvoiceRepository invoicerepo;
	
	@Override
	public Invoice saveInvoice(Invoice obj) {
		return invoicerepo.save(obj);
		
	}

	@Override
	public Invoice getInvoiceById(int invid) {
		return invoicerepo.findById(invid).get();
	}

	@Override
	public Invoice getInvoiceByCustomerId(int custid) {
		return invoicerepo.getMostRecentInvoiceByCustomerId(custid);
	}

}