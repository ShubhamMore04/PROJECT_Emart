package com.example.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.entities.*;

@Service
public interface CustomerService {
    List<Customer> getCustomers();
    void deleteCustomer(int customerId);
	public Customer saveCustomer(Customer c);
	int getRedeemPointsByID(int id);
	Optional<Customer> getCustomerByEmail(String email);
	public Customer update(Customer c,int cid);
	public boolean validateCustomer(Customer customer);
}