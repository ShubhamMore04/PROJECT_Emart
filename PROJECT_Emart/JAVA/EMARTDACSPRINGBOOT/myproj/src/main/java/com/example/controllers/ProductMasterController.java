package com.example.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.entities.ProductMaster;
import com.example.services.ProductMasterService;

@RestController
@CrossOrigin("*")
@RequestMapping("/product")
public class ProductMasterController 
{
	@Autowired
	private ProductMasterService productMasterService;
	
	@GetMapping
	public ResponseEntity<?> getAllProducts(){
		return new ResponseEntity<> ( productMasterService.getAllProducts(),HttpStatus.OK);
	}
	
	
	@GetMapping("/{productId}")
	public ResponseEntity<?> getProductId(@PathVariable int productId) {
		return new ResponseEntity<> (productMasterService.getProductById(productId),HttpStatus.OK);
	}
	
	@GetMapping("/byPriceRange")
    public ResponseEntity<List<ProductMaster>> getProductsByPriceRange(@RequestParam double minPrice, @RequestParam double maxPrice) {
        List<ProductMaster> products = productMasterService.getProductsByPriceRange(minPrice, maxPrice);
        return new ResponseEntity<>(products, HttpStatus.OK);
    }
	
	 @GetMapping("/withValidDiscount")
	    public ResponseEntity<List<ProductMaster>> getProductsWithValidDiscount() {
	        List<ProductMaster> products = productMasterService.getProductsWithValidDiscount();
	        return new ResponseEntity<>(products, HttpStatus.OK);
	    }
	
	@PostMapping
	public ResponseEntity<?> addProduct(@RequestBody ProductMaster product) {
		return new ResponseEntity<> (productMasterService.addProduct(product),HttpStatus.OK);
	}
	
	@PutMapping("/{productId}")
	public ResponseEntity<?> updateProduct(@PathVariable int productId,@RequestBody ProductMaster updatedProduct) {
		return new ResponseEntity<> ( productMasterService.updateProduct(productId,updatedProduct),HttpStatus.OK);
		
	}
	
	@DeleteMapping("/{productId}")
      public void deleteproduct(@PathVariable int productId) {
    	  productMasterService.deleteProduct(productId);
      }
	
	@GetMapping("/getCatId/{Id}")
	public ResponseEntity<?> findByCatID(@PathVariable int Id) {
		return new ResponseEntity<> (productMasterService.findByCatID(Id),HttpStatus.OK);
	}
	

}
