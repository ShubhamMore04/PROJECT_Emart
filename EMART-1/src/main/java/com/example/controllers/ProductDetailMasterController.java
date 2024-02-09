package com.example.controllers;

import com.example.entities.ProductDetailMaster;
import com.example.services.ProductDetailMasterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product-details")
public class ProductDetailMasterController {

    private final ProductDetailMasterService productDetailMasterService;

    @Autowired
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
