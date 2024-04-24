package com.example.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entities.CartMaster;
import com.example.repositeries.CartMasterRepository;

@Service
public class CartMasterServiceImpl implements CartMasterService {

	@Autowired
	private CartMasterRepository cartRepo;
	
	//Insert
	public CartMaster saveCart(CartMaster obj) {
		return cartRepo.save(obj);
	}
	
	//SelectAll
	public List<CartMaster> getAllCart() {
		return cartRepo.findAll();
	}
	
	//SelectById
	public CartMaster getCartById(int id) {
			
		return cartRepo.findById(id).get();
	}
	
	//Delete
	public void deleteCart(int id) {
			
			CartMaster c = cartRepo.findById(id).get();
			
			if(c!=null) {
				cartRepo.delete(c);
			}	
	}
	
	//Update
	public CartMaster updateCart(CartMaster c, int id) {
		CartMaster oldc =  cartRepo.findById(id).get();
		oldc.setQty(c.getQty());
		
		oldc.setQty(c.getProdID());
		oldc.setCustID(c.getCustID());
		return cartRepo.save(oldc);
		
	}	
	
	public List<CartMaster> findProdByCustID(int cid){
		return cartRepo.findProdByCustID(cid);
	}
	
	public int UpdateQty (int QT,int cid) {
		cartRepo.UpdateQty(QT, cid);
		return 1;
	}
	
	public int DeletecartByCustID (int cid) {
		cartRepo.DeletecartByCust(cid);
		return 1;
	}
	
}
