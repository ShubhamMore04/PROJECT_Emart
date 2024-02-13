package com.example.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.example.entities.Customer;
import com.example.repositories.CustomerRepository;

import jakarta.persistence.EntityNotFoundException;

@Component
public class CustomerServiceImpl implements CustomerService{

	@Autowired
	private CustomerRepository customerRepository;


	@Override
	public List<Customer> getCustomers() {

		return customerRepository.findAll();

	}


	@Override
	public void deleteCustomer(int id) {
		Customer customer = customerRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Customer not found with ID: " + id));

		if(customer!=null) {
			customerRepository.delete(customer);
		}

	}


	@Override
	public Customer saveCustomer(Customer c) {
		// TODO Auto-generated method stub
		return customerRepository.save(c);
	}


	@Override
	public int getRedeemPointsByID(int id) {

		return customerRepository.getRedeemPointsById(id);
	}


//	@Override
//	public Optional<Customer> getCustomerByEmail(String email) {
//
//		return customerRepository.getCustomerByEmail(email);
//	}


	@Override
	public Customer update(Customer c,int cid)
	{

		 Customer cust = customerRepository.findById(cid).get();
		 cust.setName(c.getName());
		 cust.setPhoneNo(c.getPhoneNo());
		 cust.setAddressLine1(c.getAddressLine1());
		 cust.setAddressLine2(c.getAddressLine2());
		 return customerRepository.save(cust);

	}
	
	@Override
	public boolean validateCustomer(Customer customer) {
	    return customerRepository.validateCustomer(customer.getEmail(), customer.getPassword());
	}


	@Override
	public Optional<Customer> getCustomerByEmail(String email) {
		// TODO Auto-generated method stub
		return Optional.empty();
	}



}