package com.example.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.entities.Customer;
import com.example.repositories.CustomerRepository;

@Component
public class CustomerServiceImpl implements CustomerService {
	
	@Autowired
	private CustomerRepository r;

	@Override
	public List<Customer> getCustomers() {
		// TODO Auto-generated method stub
		return r.findAll();
	}
}
