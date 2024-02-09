package com.example.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.services.CustomerService;

@RestController
public class CustomerController {
	
	@Autowired
	private CustomerService cservice;
	
	@GetMapping()
	public ResponseEntity<?> getCustomers(){
		
		return new ResponseEntity<>(cservice.getCustomers(),HttpStatus.OK);
		
	}
}
