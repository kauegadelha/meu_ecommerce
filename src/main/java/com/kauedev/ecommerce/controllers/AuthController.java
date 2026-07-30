package com.kauedev.ecommerce.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kauedev.ecommerce.dto.LoginRequestDTO;
import com.kauedev.ecommerce.dto.TokenDTO;
import com.kauedev.ecommerce.security.JwtService;
import com.kauedev.ecommerce.security.UserPrincipal;

import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/login")
public class AuthController {
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private JwtService jwtService;
	
	@PostMapping
	public ResponseEntity<TokenDTO> login(@Valid @RequestBody LoginRequestDTO dto){
		var authToken = new UsernamePasswordAuthenticationToken(dto.getUserName(), dto.getPassword());
		var authentication = authenticationManager.authenticate(authToken);
		
		UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
		String token = jwtService.generateToken(userPrincipal);
		
		return ResponseEntity.ok(new TokenDTO(token));
	}

}
