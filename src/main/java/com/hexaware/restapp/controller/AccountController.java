package com.hexaware.restapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hexaware.restapp.model.Account;

import com.hexaware.restapp.repository.AccountRepository;

@RestController
@RequestMapping("/account")
public class AccountController {
	//getById,findAll,deleteById,save,put
	@Autowired
	private AccountRepository accountRepository;

	


	@GetMapping("/all")
	public List<Account> findAllAccount() {
		return accountRepository.findAll();
	}

	@GetMapping("/single/{id}") // localhost:port/customer/single/6
	public Account findSingleAccount(@PathVariable("id") Long aid) {
		return accountRepository.getById(aid);
	}

	@PostMapping("/add")
	public Account postAccount(@RequestBody Account account) {
		return accountRepository.save(account);
	}

	@PutMapping("/edit/{id}")
	public Account putAccount(@PathVariable("id")Long aid,@RequestBody Account newValueAccount) {
		//go to db and fetch customer based on ID.
		Account dbAccount=accountRepository.getById(aid);
		dbAccount.setAccountType(newValueAccount.getAccountType());
		dbAccount.setIfsc(newValueAccount.getIfsc());
		
		return accountRepository.save(dbAccount);
	}

	@DeleteMapping("/delete/{id}")
	public void deleteAccount(@PathVariable("id")Long aid ) {
		accountRepository.deleteById(aid);
	}
}
