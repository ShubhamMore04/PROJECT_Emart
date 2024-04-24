package com.example.repositeries;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.entities.CustomerMaster;

@Repository
@Transactional
public interface CustomerMasterRepository extends JpaRepository <CustomerMaster,Integer> {

	@Query(value = "SELECT * FROM CustomerMaster c WHERE c.cust_email = :custEmail", nativeQuery = true)
	Optional<CustomerMaster> getCustomerByEmail(@Param("custEmail") String custEmail);

	@Query(value = "SELECT cust_email FROM CustomerMaster c WHERE c.cust_ID = :custId", nativeQuery = true)
	Optional<String> findEmailById(@Param("custId") int customerId);



	
	List<CustomerMaster> findByCardHolderTrue();
	
	@Query(value = "SELECT c.custID FROM CustomerMaster c WHERE c.custEmail = :e and c.custPassword = :p")
	int checkCust(@Param("e") String e, @Param("p") String p);
	
	@Query(value = "SELECT c.cardHolder FROM CustomerMaster c WHERE c.custID = :cid")
	boolean checkCardHolder(@Param("cid") int cid);
	
	@Query(value = "SELECT c.points FROM CustomerMaster c WHERE c.custID = :cid")
	int getPointsByID(@Param("cid") int cid);
	
}
