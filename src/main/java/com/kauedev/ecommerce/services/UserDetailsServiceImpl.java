package com.kauedev.ecommerce.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.kauedev.ecommerce.repositories.UserRepository;
import com.kauedev.ecommerce.security.UserPrincipal;

@Service
public class UserDetailsServiceImpl implements UserDetailsService{
	
	@Autowired
	private UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		return userRepository.findByUserName(userName)
				.map(UserPrincipal::new)
				.orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado: " + userName));
	}

}
