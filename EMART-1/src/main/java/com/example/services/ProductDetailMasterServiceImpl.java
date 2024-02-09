package com.example.services;

import com.example.entities.ProductDetailMaster;
import com.example.repositories.ProductDetailMasterRepository;
import com.example.services.ProductDetailMasterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductDetailMasterServiceImpl implements ProductDetailMasterService {

    @Autowired
    private ProductDetailMasterRepository productDetailMasterRepository;

    @Override
    public ProductDetailMaster saveProductDetail(ProductDetailMaster productDetail) {
        return productDetailMasterRepository.save(productDetail);
    }

    @Override
    public List<ProductDetailMaster> getAllProductDetails() {
        return productDetailMasterRepository.findAll();
    }

    @Override
    public ProductDetailMaster getProductDetailById(int id) {
        Optional<ProductDetailMaster> optionalProductDetail = productDetailMasterRepository.findById(id);
        return optionalProductDetail.orElseThrow(() -> new RuntimeException("Product Detail not found with id: " + id));
    }

    @Override
    public void deleteProductDetail(int id) {
        productDetailMasterRepository.deleteById(id);
    }
}
