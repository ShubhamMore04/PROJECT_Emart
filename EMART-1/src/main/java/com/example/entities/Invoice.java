package com.example.entities;


import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;


@Entity
public class Invoice {
	private int invid;
	private int inv_dt;
	private int Customer_id;
	private double tax;
	private int total_amt;
	private double shipping_charges;
	private double payable_amt;
	private List<Invoice_dtl> InvoiceDtList;


	public int getInv_dt() {
		return inv_dt;
	}
	
	public void setInv_dt(int inv_dt) {
		this.inv_dt = inv_dt;
	}
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public int getInvid() {
		return invid;
	}
	public void setInvid(int invid) {
		this.invid = invid;
	}
	
	
	@OneToMany(cascade=CascadeType.ALL)
	@JoinColumn(name="invoiceID")
	public List<Invoice_dtl> getInvoiceDtList() {
		return InvoiceDtList;
	}
	public void setInvoiceDtList(List<Invoice_dtl> invoiceDtList) {
		InvoiceDtList = invoiceDtList;
	}

	public int getCustid() {
		return Customer_id;
	}
	public void setCustid(int Customer_id) {
		this.Customer_id = Customer_id;
	}
	public double getTax() {
		return tax;
	}
	public void setTax(double tax) {
		this.tax = tax;
	}
	public int getTotal_amt() {
		return total_amt;
	}
	public void setTotal_amt(int total_amt) {
		this.total_amt = total_amt;
	}
	public double getPayable_amt() {
		return payable_amt;
	}
	public void setPayable_amt(double payable_amt) {
		this.payable_amt = payable_amt;
	}
	public double getShipping_charges() {
		return shipping_charges;
	}
	public void setShipping_charges(double shipping_charges) {
		this.shipping_charges = shipping_charges;
	}
}