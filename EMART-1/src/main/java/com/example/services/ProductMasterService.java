package com.example.services;



import java.util.List;

import com.example.entities.ProductMaster;

public interface ProductMasterService {
	
	List<ProductMaster> getAllProducts();
    ProductMaster getProductById(int id);
    ProductMaster saveProduct(ProductMaster product);
    void deleteProduct(int id);

}