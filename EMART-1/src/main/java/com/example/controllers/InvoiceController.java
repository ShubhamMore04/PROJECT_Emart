package com.example.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.entities.Invoice;
import com.example.services.InvoiceService;

@RestController
@RequestMapping("/invoice")
public class InvoiceController {
	@Autowired
	private InvoiceService invoiceServ;
	
	@PostMapping
	public ResponseEntity<?> AddInvoice(@RequestBody Invoice i){
		return new ResponseEntity<>(invoiceServ.saveInvoice(i),HttpStatus.CREATED);
	}
	
	@GetMapping("/{InvoiceId}")
	public ResponseEntity<?> getInvoiceById(@PathVariable int InvoiceId){
		return new ResponseEntity<>(invoiceServ.getInvoiceById(InvoiceId),HttpStatus.OK);
	}
	
	@GetMapping("/Customer/{CustomerId}")
	public ResponseEntity<?> getInvoiceByCustomerId(@PathVariable int CustomerId){
	    return new ResponseEntity<>(invoiceServ.getInvoiceByCustomerId(CustomerId), HttpStatus.OK);
	}

}