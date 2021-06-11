package com.hexaware.restapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.hexaware.restapp.model.Customer;

public interface CustomerRepository extends JpaRepository<Customer,Long>{
	List<Customer> findByName(String name);
	Customer findByContact(String contact);
	Customer findByPanAndCity(String pan,String city);
	
	//JPQL queries
	
	@Query("select c from Customer c where c.name=?1 order by c.id desc")
	List<Customer> findByNameWithOrder(String name);
	
	
	//Native query
	@Query(value="select * from customer  where name=?1 order by id desc",nativeQuery=true)
	List<Customer> findByNameWithOrderNative(String name);
	
	@Query("select c from Customer c join c.account a where a.ifsc=?1")
	List<Customer> getCustomerByAccountIFSC(String ifscCode);
	

}
