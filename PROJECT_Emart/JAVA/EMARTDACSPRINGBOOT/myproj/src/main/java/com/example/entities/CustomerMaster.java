package com.example.entities;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Entity
@Data
@RequiredArgsConstructor
@AllArgsConstructor
@Table(name = "customermaster")
public class CustomerMaster {
	

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "cust_ID")
	private int custID;
	
	
	@Column(nullable = false)
	private String custName;
	
	@Column(nullable = false)
	private String custAddress;
	

	@Column(nullable = false)
	private String custPhone;
	
	private String custEmail; 
	
	
	@Column(nullable = false)
	private String custPassword;
	
	@Column(nullable = false)
	private boolean cardHolder;
	
	@Column(nullable = false)
	private int points;
	
	
	@OneToMany(cascade=CascadeType.ALL)
	@JoinColumn(name="CustID")
	private List<InvoiceMaster> invoiceList;
	
	@OneToMany(cascade=CascadeType.ALL)
	@JoinColumn(name="CustID")
	private List<OrderMaster> orderList;
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "custID")
	private List<CartMaster> cartList;
	
	
	
	
	
}
