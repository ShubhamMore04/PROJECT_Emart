package com.example.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.entities.Customer;
import com.example.services.CustomerService;

@RestController
@RequestMapping("/customer")

public class CustomerController {
	

    @Autowired
    private CustomerService cservice;

   
    @GetMapping()
    public ResponseEntity<?> getCustomers() {
        return new ResponseEntity<>(cservice.getCustomers(),HttpStatus.OK);
    }
    @DeleteMapping("/{customerId}")
    public void deleteCustomer(@PathVariable(name = "CustomerId") int id)
	{
		cservice.deleteCustomer(id);
	}
    @PostMapping
	public  ResponseEntity<?>AddCustomer(@RequestBody Customer c)
	{
		return new ResponseEntity<>(cservice.saveCustomer(c),HttpStatus.CREATED);
	}
    @GetMapping("/points/{cid}")
    public int getPointsByID(@PathVariable int cid) {

        return cservice.getRedeemPointsByID(cid);
    }
    @GetMapping("/email/{email}")
    public Optional<Customer> getCustomerByEmail(@PathVariable String email) {

        return cservice.getCustomerByEmail(email);
    }
    @PutMapping("/update/{Customerid}")
    public ResponseEntity<?> EditCustomer(@RequestBody Customer c, @PathVariable int Customerid) {
        return new ResponseEntity<>(cservice.update(c, Customerid), HttpStatus.CREATED);
        
    }
    
    @GetMapping("/login")
    public boolean validateCustomer(@RequestBody Customer customer) {
    	System.out.println("Logged In");
    	return cservice.validateCustomer(customer);
    	}


}