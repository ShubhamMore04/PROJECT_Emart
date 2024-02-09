package com.example.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "ProductMaster")
public class ProductMaster {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "productId")
    private int productId;
    
    @Column(name = "catMasterId")
    private int catMasterId;
    
    @Column(name = "productShortDesc")
    private String productShortDesc;
    
    @Column(name = "productLongDesc", columnDefinition = "TEXT")
    private String productLongDesc;
    
    @Column(name = "MRP_Price")
    private double MRP_Price;
    
    @Column(name = "cardHoldersPrice")
    private double cardHoldersPrice;
    
    @Column(name = "points_2b_Redeem")
    private int points_2b_Redeem;
    
    @Column(name = "remarks")
    private String remarks;
    
//    @ManyToOne
//    @JoinColumn(name = "Catmaster_id", referencedColumnName = "Catmaster_id", insertable = false, updatable = false)
//    private CategoryMaster categoryMaster;

    
    public ProductMaster() {
    }

    public ProductMaster(int catMasterId, String productShortDesc, String productLongDesc, double MRP_Price, double cardHoldersPrice, int points_2b_Redeem, String remarks) {
        this.catMasterId = catMasterId;
        this.productShortDesc = productShortDesc;
        this.productLongDesc = productLongDesc;
        this.MRP_Price = MRP_Price;
        this.cardHoldersPrice = cardHoldersPrice;
        this.points_2b_Redeem = points_2b_Redeem;
        this.remarks = remarks;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getCatMasterId() {
        return catMasterId;
    }

    public void setCatMasterId(int catMasterId) {
        this.catMasterId = catMasterId;
    }

    public String getProductShortDesc() {
        return productShortDesc;
    }

    public void setProductShortDesc(String productShortDesc) {
        this.productShortDesc = productShortDesc;
    }

    public String getProductLongDesc() {
        return productLongDesc;
    }

    public void setProductLongDesc(String productLongDesc) {
        this.productLongDesc = productLongDesc;
    }

    public double getMRP_Price() {
        return MRP_Price;
    }

    public void setMRP_Price(double MRP_Price) {
        this.MRP_Price = MRP_Price;
    }

    public double getCardHoldersPrice() {
        return cardHoldersPrice;
    }

    public void setCardHoldersPrice(double cardHoldersPrice) {
        this.cardHoldersPrice = cardHoldersPrice;
    }

    public int getPoints_2b_Redeem() {
        return points_2b_Redeem;
    }

    public void setPoints_2b_Redeem(int points_2b_Redeem) {
        this.points_2b_Redeem = points_2b_Redeem;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

//    public CategoryMaster getCategoryMaster() {
//        return categoryMaster;
//    }

//    public void setCategoryMaster(CategoryMaster categoryMaster) {
//        this.categoryMaster = categoryMaster;
//    }
}









