package com.example.entities;


import jakarta.persistence.*;

@Entity
@Table(name = "Cart")
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cart_id")
    private Long cartId;

    @ManyToOne
    @JoinColumn(name = "productId", nullable = false)
    private ProductMaster product;

    @ManyToOne
    @JoinColumn(name = "cust_id", nullable = false)
    private Customer customer;

    @Column(name = "prod_qty")
    private int productQuantity;

    @Column(name = "prod_price")
    private double productPrice;

    @Column(name = "discount")
    private double discount;

    @Column(name = "delivery_charges")
    private double deliveryCharges;

    @Column(name = "total")
    private double total;


}