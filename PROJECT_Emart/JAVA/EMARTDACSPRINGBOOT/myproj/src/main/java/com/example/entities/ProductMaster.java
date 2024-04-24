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
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Entity
@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class ProductMaster {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "prod_ID")
	private int prodID;
	
	@Column(nullable = false)
	private String prodName;
	
	@Column(nullable = false)
	private String prodShortDesc;
	
	@Column(nullable = false)
	private String prodLongDesc;
	
	@Column(nullable = false)
	private double mrpPrice;
	
	private double offerPrice;
	
	@Column(name = "cardHolderPrice", nullable = false)
	private double cardHolderPrice;
	
	@Column(name = "pointsRedeem", nullable = false)
	private int pointsRedeem;
	
	@Column(name = "imgpath", nullable = false)
	private String imgPath;
	
	@Column(name = "inventoryQuantity", nullable = false)
	private int inventoryQuantity;
	
	@Column(name = "disc", nullable = true, columnDefinition = "INT DEFAULT 0")
	private int Disc;
	
	
	@Column(name = "catmasterID", nullable = false)
	private int catMasterID;
	
	@OneToMany(cascade=CascadeType.ALL)
	@JoinColumn(name="ProdID")
	private List<ConfigDetailMaster> configDetailsList;
	
	@OneToMany(cascade=CascadeType.ALL)
	@JoinColumn(name="ProdID")
	private List<InvoiceDetailsMaster> invoiceDetailsList;
	
	@OneToMany(cascade=CascadeType.ALL)
	@JoinColumn(name="ProdID")
	private List<CartMaster> cartList;
	
}

