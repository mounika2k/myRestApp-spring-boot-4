package com.hexaware.restapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.hexaware.restapp.model.User;



public interface UserRepository extends JpaRepository<User,Long>{
	@Query("select u from User u join u.role r where u.username=?1")
	User findByUsername(String username);


}
