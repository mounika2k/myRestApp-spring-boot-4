package com.hexaware.restapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hexaware.restapp.model.Role;
import com.hexaware.restapp.model.User;
import com.hexaware.restapp.repository.RoleRepository;
import com.hexaware.restapp.repository.UserRepository;



@RestController
@RequestMapping("/user")
public class UserController {
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private RoleRepository roleRepository;
	@Autowired
	private PasswordEncoder passwordEncoder;
	
//	@PostMapping("/add")
//	public User postUser(@RequestBody User user) {
//		return userRepository.save(user);
//	}
//	@PostMapping("/role/add")
//	public Role postRole(@RequestBody Role role) {
//		return roleRepository.save(role);
//	}
//	@PostMapping("/role/assign/{uid}/{rid}")
//	public User assignAccountToCustomer(@PathVariable("uid") Long uid,@PathVariable("rid") Long rid) {
//		User user=userRepository.getById(uid);
//		Role role=roleRepository.getById(rid);
//		List<Role> userRoleList=user.getRole();
//		userRoleList.add(role);
//		return userRepository.save(user);
//		
//		}
	@PostMapping("/add")
	public User addUser(@RequestBody User user) {
		List<Role> roles=roleRepository.saveAll(user.getRole());
		user.setRole(roles);
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		return userRepository.save(user);
		
	}
	@GetMapping("/single")
	public User getUser() {
		System.out.println(userRepository.findByUsername("micheal"));
		return userRepository.findByUsername("micheal");
	}
	
	

}
