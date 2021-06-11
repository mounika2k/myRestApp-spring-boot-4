package com.hexaware.restapp.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.hexaware.restapp.model.Role;
import com.hexaware.restapp.model.User;
import com.hexaware.restapp.repository.UserRepository;

@Service
public class MyUserDetailsService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// go to db and fetch user record and map it to spring internal user
		System.out.println("here l;Line 26...");
		User user = userRepository.findByUsername(username);
		System.out.println("here l;Line 28...");
	 
		List<GrantedAuthority> list = new ArrayList<>();
		
		 for(Role r : user.getRole()) {
			 SimpleGrantedAuthority sga = new SimpleGrantedAuthority(r.getName());
			 list.add(sga); 
		  } 
		 
		 
		 return new org.springframework.security.core.userdetails.User(user.getUsername(),
					user.getPassword(),list );	}
}