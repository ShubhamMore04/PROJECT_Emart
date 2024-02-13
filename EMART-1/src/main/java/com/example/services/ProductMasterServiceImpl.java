package com.example.services;


import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;

import com.example.entities.ProductMaster;
import com.example.repositories.ProductMasterRepository;

@Component
public class ProductMasterServiceImpl implements ProductMasterService{

	private ProductMasterRepository productMasterRepository;

    public ProductMasterServiceImpl(ProductMasterRepository productMasterRepository) {
        this.productMasterRepository = productMasterRepository;
    }

    @Override
    public List<ProductMaster> getAllProducts() {
        return productMasterRepository.findAll();
    }

    @Override
    public ProductMaster getProductById(int id) {
        Optional<ProductMaster> optionalProduct = productMasterRepository.findById(id);
        return optionalProduct.orElse(null);
    }

    @Override
    public ProductMaster saveProduct(ProductMaster product) {
        return productMasterRepository.save(product);
    }

    @Override
    public void deleteProduct(int id) {
        productMasterRepository.deleteById(id);
    }

}