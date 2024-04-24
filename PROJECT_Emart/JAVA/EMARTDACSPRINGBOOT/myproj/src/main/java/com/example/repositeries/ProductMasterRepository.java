package com.example.repositeries;



import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.entities.ProductMaster;

import jakarta.transaction.Transactional;
@Repository
@Transactional
public interface ProductMasterRepository extends JpaRepository<ProductMaster, Integer> 
{
	List<ProductMaster> findProductsByMrpPriceBetween(double minPrice, double maxPrice);

	
	default List<ProductMaster> findProductsWithValidDiscount() {
        List<ProductMaster> allProducts = findAll();
        return allProducts.stream()
                .filter(product -> product.getOfferPrice() < product.getMrpPrice())
                .collect(Collectors.toList());
    }
	
	@Query("SELECT p FROM ProductMaster p WHERE p.catMasterID = :catId")
    List<ProductMaster> findByCatID(@Param("catId") int catId);
	
	
	
//	 the default implementation of findProductsWithValidDiscount() provides a convenient way to define custom behavior in your 
//	 repository without requiring every implementing class to provide the same implementation. If a specific repository needs to 
//	 customize this logic, it can simply override the method. The default keyword allows you to add behavior to an interface without 
//	breaking backward compatibility for classes implementing the interface.
}
