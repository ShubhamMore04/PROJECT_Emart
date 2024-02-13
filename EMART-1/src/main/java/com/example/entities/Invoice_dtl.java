package com.example.entities;
import java.time.LocalDateTime;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Invoice_dtl {
	private int invdtl_id;
	private int invid;
	private int productId;
	private double mrp;
	private double card_holder_price;
	private double points_redeemed;
	private LocalDateTime created_at;

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public int getInvdtl_id() {
		return invdtl_id;
	}
	public void setInvdtl_id(int invdtl_id) {
		this.invdtl_id = invdtl_id;
	}
	
	public int getInvid() {
		return invid;
	}
	public void setInvid(int invid) {
		this.invid = invid;
	}
	public int getProdid() {
		return productId;
	}
	public void setProdid(int productId) {
		this.productId = productId;
	}
	public double getMrp() {
		return mrp;
	}
	public void setMrp(double mrp) {
		this.mrp = mrp;
	}
	public double getCard_holder_price() {
		return card_holder_price;
	}
	public void setCard_holder_price(double card_holder_price) {
		this.card_holder_price = card_holder_price;
	}
	public double getPoints_redeemed() {
		return points_redeemed;
	}
	public void setPoints_redeemed(double points_redeemed) {
		this.points_redeemed = points_redeemed;
	}
	public LocalDateTime getCreated_at() {
		return created_at;
	}
	public void setCreated_at(LocalDateTime created_at) {
		this.created_at = created_at;
	}
	

}