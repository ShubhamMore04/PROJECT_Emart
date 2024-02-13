package com.example.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.entities.Invoice_dtl;
import com.example.services.Invoice_dtlService;

@RestController
@RequestMapping("/invoiceDetails")
public class Invoice_dtlController {
	@Autowired
	private Invoice_dtlService invoice_dtlServ;
	
	@PostMapping
	public ResponseEntity<?> AddInvoice(@RequestBody Invoice_dtl i){
		return new ResponseEntity<>(invoice_dtlServ.addInvoice_details(i),HttpStatus.CREATED);
	}
	
	
	@GetMapping("/Inv/{invdtl_id}")
	public ResponseEntity<?> getInvoiceDetailsById(@PathVariable int invdtl_id){
	    return new ResponseEntity<>(invoice_dtlServ.getInvoice_detailsById(invdtl_id),HttpStatus.OK);
	}

	
	@GetMapping("/{InvoiceId}")
	public ResponseEntity<?> getInvoiceByCustomerId(@PathVariable int InvoiceId){
		return new ResponseEntity<>(invoice_dtlServ.getInvoice_detailsByInvoiceId(InvoiceId), HttpStatus.OK);
	}
}