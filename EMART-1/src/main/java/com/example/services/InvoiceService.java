package com.example.services;

import com.example.entities.Invoice;

public interface InvoiceService {
	Invoice saveInvoice(Invoice obj);
	Invoice getInvoiceById(int id);
	Invoice getInvoiceByCustomerId(int id);
}