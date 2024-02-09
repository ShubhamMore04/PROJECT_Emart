package com.example.services;

import com.example.entities.ProductDetailMaster;

import java.util.List;

public interface ProductDetailMasterService {
	
    ProductDetailMaster saveProductDetail(ProductDetailMaster productDetail);
    List<ProductDetailMaster> getAllProductDetails();
    ProductDetailMaster getProductDetailById(int id);
    void deleteProductDetail(int id);
}
