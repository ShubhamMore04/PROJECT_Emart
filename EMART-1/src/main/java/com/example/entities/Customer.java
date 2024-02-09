package com.example.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Customer {

	private int customerId;
	private String Name;
	private String phoneNo;
	private String email;
	private char gender;
	private int reedeemPoints;
	private String addressLine_1;
	private String addressLine_2;
	private String city;
	private String pincode;
	private String password;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public int getCustomerId() {
		return customerId;
	}
	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
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
	public String getAddressLine_1() {
		return addressLine_1;
	}
	public void setAddressLine_1(String addressLine_1) {
		this.addressLine_1 = addressLine_1;
	}
	public String getAddressLine_2() {
		return addressLine_2;
	}
	public void setAddressLine_2(String addressLine_2) {
		this.addressLine_2 = addressLine_2;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getPincode() {
		return pincode;
	}
	public void setPincode(String pincode) {
		this.pincode = pincode;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
}
