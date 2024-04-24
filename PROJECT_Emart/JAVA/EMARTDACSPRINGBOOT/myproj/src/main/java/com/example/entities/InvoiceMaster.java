package com.example.entities;

import java.sql.Date;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Entity
@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class InvoiceMaster {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "Invoice_id")
	private int invoiceID;
	private Date InvoiceDate;
	
	private double totalAmt;
	private double tax;
	private double deliveryCharge;
	private double TotalBill;
	private int custID;
	
	@OneToMany(cascade=CascadeType.ALL)
	@JoinColumn(name="invoiceID")
	private List<InvoiceDetailsMaster> InvoiceDtList;
	
	@OneToMany(cascade=CascadeType.ALL)
	@JoinColumn(name="invoiceID")
	private List<OrderMaster> Olist;
	
	
	

}
