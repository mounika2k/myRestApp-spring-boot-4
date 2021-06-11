package com.hexaware.restapp.controller;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hexaware.restapp.model.Account;
import com.hexaware.restapp.model.Customer;
import com.hexaware.restapp.repository.AccountRepository;
import com.hexaware.restapp.repository.CustomerRepository;

@RestController
@RequestMapping("/customer")
public class CustomerController {

	@Autowired
	private CustomerRepository customRepository;
	@Autowired
	private AccountRepository accountRepository;

	@GetMapping("/hello")
	public String sayHello() {
		return "Hello Spring Boot";
	}

	// Get: findAll : findById

	@GetMapping("/all")
	public List<Customer> findAllCustomer() {
		return customRepository.findAll();
	}

	@GetMapping("/single/{id}") // localhost:port/customer/single/6
	public Customer findSingleCustomer(@PathVariable("id") Long cid) {
		return customRepository.getById(cid);
	}

	@PostMapping("/add")
	public Customer postCustomer(@RequestBody Customer customer) {
		return customRepository.save(customer);
	}

	@PutMapping("/edit/{id}")
	public Customer putCustomer(@PathVariable("id")Long cid,@RequestBody Customer newValueCustomer) {
		//go to db and fetch customer based on ID.
		Customer dbCustomer=customRepository.getById(cid);
		dbCustomer.setContact(newValueCustomer.getContact());
		dbCustomer.setName(newValueCustomer.getName());
		dbCustomer.setCity(newValueCustomer.getCity());
		
		return customRepository.save(dbCustomer);
	}

	@DeleteMapping("/delete/{id}")
	public void deleteCustomer(@PathVariable("id")Long cid ) {
		customRepository.deleteById(cid);
	}
		
	@GetMapping("/name/{name}")
	public List<Customer> getCustomerByName(@PathVariable("name")String name){
		return customRepository.findByName(name);
	}
	@GetMapping("/contact/{contact}")
	public Customer getCustomerByContact(@PathVariable("contact")String contact){
		return customRepository.findByContact(contact);
	}
	@GetMapping("/pan/city/{pan}/{city}")
	public Customer getCustomerByPanAndCity(@PathVariable("pan")String pan,@PathVariable("city")String city){
		return customRepository.findByPanAndCity(pan,city);
	}
	@GetMapping("/name/order")
	public List<Customer> getfindByNameWithOrder(@RequestParam("name")String name){
		return customRepository.findByNameWithOrder(name);
	}
	@GetMapping("/name/order/native")
	public List<Customer> getfindByNameWithOrderNative(@RequestParam("name")String name){
		return customRepository.findByNameWithOrderNative(name);
	}
	
	@PostMapping("/account/assign/{cid}/{aid}")
	public Customer assignAccountToCustomer(@PathVariable("cid") Long cid,@PathVariable("aid") Long aid) {
		Customer customer=customRepository.getById(cid);
		Account account=accountRepository.getById(aid);
		List<Account> customerAccountList=customer.getAccount();
		customerAccountList.add(account);
		return customRepository.save(customer);
		
		}
	@GetMapping("/account/ifsc/{ifscCode}")
	public List<Customer> getCustomerByAccountIFSC(@PathVariable("ifscCode")String ifscCode){
		List<Customer> list=
				customRepository.getCustomerByAccountIFSC(ifscCode);
			return list;
	}
	@GetMapping("/account/ifsc/without-duplicates/{ifscCode}")
	public Set<Customer> getCustomerByAccountIFSCWithoutDuplicatesUsingTreeSet(@PathVariable("ifscCode")String ifscCode){
		List<Customer> list=
				customRepository.getCustomerByAccountIFSC(ifscCode);
		//remove duplicates if any
		Set<Customer> set=new TreeSet<>(list);
			return set;
	}
	
}