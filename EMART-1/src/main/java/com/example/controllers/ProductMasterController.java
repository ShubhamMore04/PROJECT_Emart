package com.example.controllers;

import com.example.entities.ProductMaster;
import com.example.services.ProductMasterService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductMasterController {

    private final ProductMasterService productMasterService;

    public ProductMasterController(ProductMasterService productMasterService) {
        this.productMasterService = productMasterService;
    }

    @GetMapping
    public ResponseEntity<List<ProductMaster>> getAllProducts() {
        List<ProductMaster> products = productMasterService.getAllProducts();
        return ResponseEntity.ok(products);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductMaster> getProductById(@PathVariable int id) {
        ProductMaster product = productMasterService.getProductById(id);
        if (product != null) {
            return ResponseEntity.ok(product);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<ProductMaster> createProduct(@RequestBody ProductMaster product) {
        ProductMaster savedProduct = productMasterService.saveProduct(product);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedProduct);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable int id) {
        productMasterService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }
}
