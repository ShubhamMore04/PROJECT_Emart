package com.example.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.example.entities.*;

import jakarta.transaction.Transactional;

@Repository
@Transactional	 	
public interface CustomerRepository extends JpaRepository<Customer, Integer> {
    @Query(value = "SELECT c.reedeem_points FROM Customer c WHERE c.customer_id = :customer_id", nativeQuery = true)
    int getRedeemPointsById(@Param("customer_id") int cid);

    @Query(value = "SELECT * FROM Customer c WHERE c.email = :cEmail", nativeQuery = true)
	Optional<Customer> getCustomerByEmail(@Param("cEmail") String cEmail);
    
    //@Query(value="SELECT count(c.id)>0 FROM Customer c WHERE c.email = :email AND c.password =: password", nativeQuery = true)
    @Query("SELECT count(*)>=1 FROM Customer c WHERE  c.email = :email AND c.password= :password")
    boolean validateCustomer(@Param("email") String email, @Param("password") String password);
}
