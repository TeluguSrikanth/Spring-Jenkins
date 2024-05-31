package com.hostwire.jwt.config;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.hostwire.jwt.entity.UserInfi;
import com.hostwire.jwt.repository.UserInfiRepository;

@Component
public class UserInfiUserDetailsService implements UserDetailsService {

	@Autowired
	private UserInfiRepository repository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<UserInfi> userInfo=repository.findByName(username);
		 return userInfo.map(UserInfiUserDetails::new)
	                .orElseThrow(() -> new UsernameNotFoundException("user not found " + username));

	}

}
