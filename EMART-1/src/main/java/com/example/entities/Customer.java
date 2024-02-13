package com.example.entities;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;

@Entity
public class Customer {


	public int Customer_id;

    public String name;
 	public String phoneNo;
 	public String email;
 	public char gender;
	public int reedeemPoints;
	public String  addressLine1;
 	public String  addressLine2;
	public int  pincode;
 	public String password;
 	 @Id
     @GeneratedValue(strategy=GenerationType.IDENTITY)
 	public int getCustomerId() {
		return Customer_id;
	}
 	 @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, orphanRemoval = true)
     private List<Order> orders = new ArrayList<>();
 	public void setCustomerId(int i) {
 		this.Customer_id=i;
 	}
 	
 	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "Customer_id")
	private List<Invoice> invoiceList;
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "Customer_id")
	private List<Order> orderList;

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhoneNo() {
		return phoneNo;
	}
	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public char getGender() {
		return gender;
	}
	public void setGender(char gender) {
		this.gender = gender;
	}
	public int getReedeemPoints() {
		return reedeemPoints;
	}
	public void setReedeemPoints(int reedeemPoints) {
		this.reedeemPoints = reedeemPoints;
	}
	public String getAddressLine1() {
		return addressLine1;
	}
	public void setAddressLine1(String addressLine1) {
		this.addressLine1 = addressLine1;
	}
	public String getAddressLine2() {
		return addressLine2;
	}
	public void setAddressLine2(String addressLine2) {
		this.addressLine2 = addressLine2;
	}
	public int getPincode() {
		return pincode;
	}
	public void setPincode(int pincode) {
		this.pincode = pincode;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

}