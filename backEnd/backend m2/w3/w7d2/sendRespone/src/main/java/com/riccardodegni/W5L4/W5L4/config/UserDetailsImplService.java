package com.riccardodegni.W5L4.W5L4.config;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.riccardodegni.W5L4.W5L4.dao.DaoService;
import com.riccardodegni.W5L4.W5L4.entities.User;

@Service
public class UserDetailsImplService implements UserDetailsService {
	
	@Autowired
	private DaoService dao;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<User> user = dao.getUserByUsername(username);
		
		if( user.isPresent() ) {
			return UserDetailsImpl.build( user.get() );
		}
		else {
			throw new UsernameNotFoundException(username);
		}
		
		//return null;
	}
	
}
