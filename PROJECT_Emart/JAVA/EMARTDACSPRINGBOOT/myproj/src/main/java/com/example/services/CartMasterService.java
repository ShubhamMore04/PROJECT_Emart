package com.example.services;

import java.util.List;

import com.example.entities.CartMaster;

public interface CartMasterService {
	CartMaster saveCart(CartMaster obj);
	List<CartMaster> getAllCart();
	CartMaster getCartById(int id);
	void deleteCart(int id);
	CartMaster updateCart(CartMaster c, int id);
	List<CartMaster> findProdByCustID(int cid);
	int UpdateQty (int QT,int cid);
	int DeletecartByCustID (int cid);
}
