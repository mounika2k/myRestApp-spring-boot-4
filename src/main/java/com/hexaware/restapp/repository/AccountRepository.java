package com.hexaware.restapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hexaware.restapp.model.Account;



public interface AccountRepository extends JpaRepository<Account,Long>{

}
