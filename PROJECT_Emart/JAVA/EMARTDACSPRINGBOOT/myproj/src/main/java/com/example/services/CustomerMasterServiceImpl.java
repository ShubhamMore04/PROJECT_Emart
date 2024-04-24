package com.example.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entities.CustomerMaster;
import com.example.repositeries.CustomerMasterRepository;

@Service
public class CustomerMasterServiceImpl implements CustomerMasterService {

	
	@Autowired
	private CustomerMasterRepository customerRepo;
	

	public CustomerMaster saveCustomer(CustomerMaster c)
	{
		return customerRepo.save(c);
	}
	
	 public CustomerMaster getCustomerById(int cid)
	 {
		return customerRepo.findById(cid).get();
	 }
	
	public List<CustomerMaster>getAllCustomers()
	{
		return customerRepo.findAll();
	}
	
	
	public void deleteCustomer(int id)
	{
		CustomerMaster cust = customerRepo.findById(id).get();
		
		if(cust!=null) {
		 customerRepo.delete(cust);
		}
	}
	
	@Override
    public Optional<CustomerMaster> getCustomerByEmail(String email) {
        return customerRepo.getCustomerByEmail(email);
    }
	
	

	

    @Override
    public List<CustomerMaster> getPrimeCustomers() {
        return customerRepo.findByCardHolderTrue();
    }
	
	public CustomerMaster update(CustomerMaster c,int cid)
	{
		
		 CustomerMaster cust = customerRepo.findById(cid).get();
		 cust.setCustName(c.getCustName());
		 cust.setCustPhone(c.getCustPhone());
		 cust.setCustAddress(c.getCustAddress());
		 cust.setCustEmail(c.getCustEmail());
		 return customerRepo.save(cust);
		
	}
	
	public int checkCust(String e, String p) {
		
		return customerRepo.checkCust(e,p);
	}
	
	public boolean checkCardHolder(int id) {
		return customerRepo.checkCardHolder(id);
	}
	
	public int getPointsByID(int id) {
		return customerRepo.getPointsByID(id);
	}

	@Override
	public Optional<String> getCustomerEmailById(int customerId) {
		return customerRepo.findEmailById(customerId);
	}

	@Override
	public Optional<CustomerMaster> findById(int custId) {
	    return customerRepo.findById(custId);
	}

	

	
	
}
