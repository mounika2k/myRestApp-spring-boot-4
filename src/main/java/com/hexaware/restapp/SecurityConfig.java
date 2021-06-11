package com.hexaware.restapp;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	@Override
	protected void configure(AuthenticationManagerBuilder auth)throws Exception{
		//Build custom AuthManager
		auth.inMemoryAuthentication()
		.withUser("peter").password(getPasswordEncoder().encode("parker"))
		.authorities("EXECUTIVE")
		.and()
		.withUser("micheal").password(getPasswordEncoder().encode("scott"))
		.authorities("MANAGER")
		.and()
		.withUser("pam").password(getPasswordEncoder().encode("halpert"))
		.authorities("SALES");
		
		
	}
	@Override
	protected void configure(HttpSecurity http)throws Exception{
		//Defining API rules
		http.authorizeRequests()
		.antMatchers("/user/single").permitAll()
		.antMatchers("/user/aadd").permitAll()
		.antMatchers("/customer/all").authenticated()
		.antMatchers("/customer/single/**").permitAll()
		.antMatchers("/customer/edit/**").hasAuthority("MANAGER")
		.antMatchers("/customer/add").hasAnyAuthority("MANAGER","SALES")
		.and()
		.httpBasic();
		
		http.cors();
		http.csrf().disable();
		
	}
	@Primary
	@Bean
	public PasswordEncoder getPasswordEncoder(){
		return new BCryptPasswordEncoder();
	}
	

}
