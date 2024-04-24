package com.example.repositeries;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.entities.CartMaster;

import jakarta.transaction.Transactional;


@Repository
@Transactional
public interface CartMasterRepository extends JpaRepository<CartMaster,Integer> {

	@Query(value = "SELECT * FROM CartMaster c WHERE c.CustID = :custID", nativeQuery = true)
    List<CartMaster> findProdByCustID(@Param("custID") int custID);
	
	@Modifying
	@Query(value = "UPDATE CartMaster c SET c.Qty = :newQty WHERE c.Cart_ID = :cartId", nativeQuery = true)
	void UpdateQty(@Param("newQty") int QT,@Param("cartId") int cid );
	
	@Modifying
	@Query(value = "DELETE FROM CartMaster WHERE custid = :cid", nativeQuery = true)
	void DeletecartByCust (@Param("cid") int cid);
	
	
}
