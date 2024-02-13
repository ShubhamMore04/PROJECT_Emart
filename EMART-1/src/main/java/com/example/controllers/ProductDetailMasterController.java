package com.example.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.entities.ProductDetailMaster;
import com.example.services.ProductDetailMasterService;

@RestController
@RequestMapping("/product-details")
public class ProductDetailMasterController {
	 @Autowired
    private final ProductDetailMasterService productDetailMasterService;

   
    public ProductDetailMasterController(ProductDetailMasterService productDetailMasterService) {
        this.productDetailMasterService = productDetailMasterService;
    }

    @GetMapping
    public ResponseEntity<List<ProductDetailMaster>> getAllProductDetails() {
        List<ProductDetailMaster> productDetails = productDetailMasterService.getAllProductDetails();
        return ResponseEntity.ok(productDetails);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDetailMaster> getProductDetailById(@PathVariable int id) {
        ProductDetailMaster productDetail = productDetailMasterService.getProductDetailById(id);
        return ResponseEntity.ok(productDetail);
    }

    @PostMapping
    public ResponseEntity<ProductDetailMaster> createProductDetail(@RequestBody ProductDetailMaster productDetail) {
        ProductDetailMaster savedProductDetail = productDetailMasterService.saveProductDetail(productDetail);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedProductDetail);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProductDetail(@PathVariable int id) {
        productDetailMasterService.deleteProductDetail(id);
        return ResponseEntity.noContent().build();
    }
}