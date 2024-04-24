package com.example.controllers;

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
import org.springframework.web.bind.annotation.RestController;

import com.example.entities.CartMaster;
import com.example.services.CartMasterService;

@RestController
@CrossOrigin("*")
@RequestMapping("/Cart")
public class CartMasterController {

	@Autowired
	private CartMasterService cartServ;
	
	@PostMapping
	public ResponseEntity<?> AddCart (@RequestBody CartMaster c){
		return new ResponseEntity<> (cartServ.saveCart(c),HttpStatus.CREATED);
	}
	
	@GetMapping
	public ResponseEntity<?> getAllCart (){
		return new ResponseEntity<> (cartServ.getAllCart(),HttpStatus.OK);
	}
	
	@GetMapping("/{CartId}")
	public ResponseEntity<?> getCartByID(@PathVariable int id){
		return new ResponseEntity<> (cartServ.getCartById(id),HttpStatus.OK);
	}
	
	@DeleteMapping("/{CartId}")
	public void deleteCart(@PathVariable int CartId){
		cartServ.deleteCart(CartId);
	}
	
	@PutMapping("/{Cartid}")
	public ResponseEntity<?> editCart (@RequestBody CartMaster c,@PathVariable int id){
		return new ResponseEntity<> (cartServ.updateCart(c, id),HttpStatus.CREATED);
	}
	
	@GetMapping("/cust/{cid}")
	public ResponseEntity<?> getProdByCustID(@PathVariable int cid){
		return new ResponseEntity<> (cartServ.findProdByCustID(cid),HttpStatus.OK);
	}
	
	@PutMapping("/{qty}/{Cartid}")
	public ResponseEntity<?> UpdateQty (@PathVariable int qty, @PathVariable int Cartid){
		return new ResponseEntity<> (cartServ.UpdateQty(qty, Cartid),HttpStatus.OK);
	}
	
	@DeleteMapping("/Deletecust/{CustId}")
	public ResponseEntity<?> DeletecartByCustID(@PathVariable int CustId){
		return new ResponseEntity<> (cartServ.DeletecartByCustID(CustId),HttpStatus.OK);
	}
	
}
