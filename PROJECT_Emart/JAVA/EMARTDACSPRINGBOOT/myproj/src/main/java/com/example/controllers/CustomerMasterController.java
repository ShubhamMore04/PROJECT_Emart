package com.example.controllers;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.entities.Authentication;
import com.example.entities.CustomerMaster;
import com.example.services.CustomerMasterService;

@RestController
@RequestMapping("/Customer")
@CrossOrigin("*")
public class CustomerMasterController {
	
	@Autowired
	private CustomerMasterService customerMasterService;
	
	@GetMapping
	public ResponseEntity<?> getAllCustomers()
	{
		return new ResponseEntity<>(customerMasterService.getAllCustomers(),HttpStatus.OK);
	}
	@GetMapping("/customer/{CustomerId}")
	public ResponseEntity<?> getCustomerById(@PathVariable int CustomerId )
	{
	    return new ResponseEntity<>(customerMasterService.getCustomerById(CustomerId),HttpStatus.OK);
	}
	@GetMapping("/byEmail/{email}")
    public ResponseEntity<CustomerMaster> getCustomerByEmail(@PathVariable String email) {
        Optional<CustomerMaster> customer = customerMasterService.getCustomerByEmail(email);
        
        if (customer.isPresent()) {
            return ResponseEntity.ok(customer.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }
	@GetMapping("/byId/{customerId}")
	public ResponseEntity<String> getCustomerEmailById(@PathVariable int customerId) {
	    Optional<String> customerEmail = customerMasterService.getCustomerEmailById(customerId);

	    if (customerEmail.isPresent()) {
	        return ResponseEntity.ok(customerEmail.get());
	    } else {
	        return ResponseEntity.notFound().build();
	    }
	}

	
	@GetMapping("/prime")
    public ResponseEntity<List<CustomerMaster>> getPrimeCustomers() {
        List<CustomerMaster> primeCustomers = customerMasterService.getPrimeCustomers();
        return new ResponseEntity<>(primeCustomers,HttpStatus.OK);
    }
	
	
	@PostMapping
	public  ResponseEntity<?>AddCustomer(@RequestBody CustomerMaster c)
	{
		return new ResponseEntity<>(customerMasterService.saveCustomer(c),HttpStatus.CREATED);
	}
	
	@PutMapping("/{Customerid}")
	public ResponseEntity<?> EditCustomer(@RequestBody CustomerMaster c,@PathVariable int id )
	{
		return new ResponseEntity<>(customerMasterService.update(c, id),HttpStatus.CREATED);
	}
	
	@DeleteMapping("/{Customerid}")
	public void deleteCustomer(@PathVariable int id)
	{
		customerMasterService.deleteCustomer(id);
	}
	
	@PostMapping("/login")
	public int checkCust(@RequestBody Authentication a) {
		return customerMasterService.checkCust(a.getCustEmail(), a.getCustPassword());
	}
	
	@GetMapping("/isCardHolder/{cid}")
    public boolean checkCardHolder(@PathVariable int cid) {
        
        return customerMasterService.checkCardHolder(cid);
    }
	@PutMapping("/points/{custId}/{newPoints}")
	public ResponseEntity<?> updateCustomerPoints(@PathVariable int custId, @PathVariable int newPoints) {
	    Optional<CustomerMaster> customerOptional = customerMasterService.findById(custId);

	    if (!customerOptional.isPresent()) {
	        return ResponseEntity.notFound().build();
	    }

	    CustomerMaster customer = customerOptional.get();
	    customer.setPoints(newPoints);
	    customerMasterService.saveCustomer(customer); // Use customerMasterService instead of customerRepository

	    return ResponseEntity.noContent().build();
	}

	
	@GetMapping("/points/{cid}")
    public int getPointsByID(@PathVariable int cid) {
        
        return customerMasterService.getPointsByID(cid);
    }
	
	

}