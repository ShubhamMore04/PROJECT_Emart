package com.example.services;

import java.util.List;
import java.util.Optional;

import com.example.entities.CustomerMaster;

public interface CustomerMasterService {
	
	public CustomerMaster saveCustomer(CustomerMaster c);
	public List<CustomerMaster>getAllCustomers();
	public void deleteCustomer(int cid);
	public CustomerMaster update(CustomerMaster c,int cid);
	public CustomerMaster getCustomerById(int cid);
	Optional<CustomerMaster> getCustomerByEmail(String email);
	public Optional<String> getCustomerEmailById(int customerId);
	List<CustomerMaster> getPrimeCustomers();
	int checkCust(String e, String p);
	boolean checkCardHolder(int id);
	int getPointsByID(int id);
	public Optional<CustomerMaster> findById(int custId);

}
