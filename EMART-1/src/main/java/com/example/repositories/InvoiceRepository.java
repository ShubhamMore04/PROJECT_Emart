package com.example.repositories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param; 
import org.springframework.stereotype.Repository;
import jakarta.transaction.Transactional;
import com.example.entities.Invoice;


@Repository
@Transactional
public interface InvoiceRepository extends JpaRepository<Invoice, Integer> {

	@Query(value = "SELECT * FROM Invoice WHERE custid = :custid ORDER BY inv_dt DESC LIMIT 1", nativeQuery = true)
	Invoice getMostRecentInvoiceByCustomerId(@Param("custid") int custid);



}