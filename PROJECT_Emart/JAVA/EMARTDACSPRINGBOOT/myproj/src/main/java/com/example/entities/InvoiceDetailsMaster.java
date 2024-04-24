package com.example.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Entity
@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class InvoiceDetailsMaster {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "InvoiceDt_ID")
	private int InvoiceDtID;
	private double mrp;
	private double CardHolderPrice;
	private int PointsRedeem;
	private int invoiceID;
	private int ProdID;
	private String prodName;
	
	
	
}
