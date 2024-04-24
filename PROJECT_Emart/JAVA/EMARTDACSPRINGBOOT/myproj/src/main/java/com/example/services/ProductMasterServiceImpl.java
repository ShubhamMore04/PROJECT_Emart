package com.example.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entities.ProductMaster;
import com.example.repositeries.ProductMasterRepository;

@Service
public class ProductMasterServiceImpl implements ProductMasterService
{
	@Autowired
	private ProductMasterRepository productMasterRepository;

	@Override
	public List<ProductMaster> getAllProducts() {
		
		return productMasterRepository.findAll();
	}

	@Override
	public ProductMaster getProductById(int productId) {
		
		
		return productMasterRepository.findById(productId).get(); 
	}

	@Override
	public ProductMaster addProduct(ProductMaster product) {
		
		return productMasterRepository.save(product);
	}

	@Override
	public void deleteProduct(int productId) {
		ProductMaster c = productMasterRepository.findById(productId).get();
		
		if(c!=null) {
		   productMasterRepository.delete(c);
		}	
		
	}

    @Override
   
    public ProductMaster updateProduct(int productId, ProductMaster updatedProduct) {
        ProductMaster existingProduct = productMasterRepository.findById(productId).orElse(null);

        if (existingProduct != null) {
            existingProduct.setProdName(updatedProduct.getProdName());
            existingProduct.setProdShortDesc(updatedProduct.getProdShortDesc());
            existingProduct.setProdLongDesc(updatedProduct.getProdLongDesc());
            existingProduct.setMrpPrice(updatedProduct.getMrpPrice());
            existingProduct.setOfferPrice(updatedProduct.getOfferPrice());
            existingProduct.setCardHolderPrice(updatedProduct.getCardHolderPrice());
            existingProduct.setPointsRedeem(updatedProduct.getPointsRedeem());
            existingProduct.setImgPath(updatedProduct.getImgPath());
            existingProduct.setInventoryQuantity(updatedProduct.getInventoryQuantity());

           
            return productMasterRepository.save(existingProduct);
        }

        return null;
    }
    
    @Override
    public List<ProductMaster> getProductsByPriceRange(double minPrice, double maxPrice) {
        return productMasterRepository.findProductsByMrpPriceBetween(minPrice, maxPrice);
    }
     
    @Override
    public List<ProductMaster> getProductsWithValidDiscount() {
        return productMasterRepository.findProductsWithValidDiscount();
    }
    
    @Override
	public List<ProductMaster> findByCatID(int id) {
		// TODO Auto-generated method stub
		return productMasterRepository.findByCatID(id);
	}

}
