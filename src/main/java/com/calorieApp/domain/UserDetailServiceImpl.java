package com.calorieApp.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserDetailServiceImpl implements UserDetailsService {
	private final UserRepository repository;
	
	@Bean
	PasswordEncoder getPasswordEncoder() {
	    return new BCryptPasswordEncoder();
	}

	@Autowired
	public UserDetailServiceImpl(UserRepository userRepository) {
		this.repository = userRepository;
	}

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		User curruser = repository.findByEmail(email);
		UserDetails user = new org.springframework.security.core.userdetails.User(email, curruser.getPassword(),
				AuthorityUtils.createAuthorityList(curruser.getRole()));
		return user;
	}

}