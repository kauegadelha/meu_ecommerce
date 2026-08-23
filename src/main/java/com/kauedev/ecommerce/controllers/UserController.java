package com.kauedev.ecommerce.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kauedev.ecommerce.dto.UserDTO;
import com.kauedev.ecommerce.dto.UserInsertDTO;
import com.kauedev.ecommerce.dto.UserUpdateDTO;
import com.kauedev.ecommerce.security.UserPrincipal;
import com.kauedev.ecommerce.services.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/user")
public class UserController {
	@Autowired
	private UserService userService;
	
	@GetMapping(value = "/me")
	public ResponseEntity<UserDTO> findMe(@AuthenticationPrincipal UserPrincipal userPrincipal) {
		UserDTO dto = new UserDTO(userPrincipal.getUser());
		return ResponseEntity.ok(dto);
	}

	@PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')")
	@GetMapping(value = "/name")
	public ResponseEntity<UserDTO> findUser(@RequestParam String name) {
		UserDTO dto = userService.findUser(name);
		return ResponseEntity.ok(dto);
	}
	
	@PostMapping()
	public ResponseEntity<UserDTO> insertUser(@Valid @RequestBody UserInsertDTO dto) {
		UserDTO user = userService.insertUser(dto);
		return ResponseEntity.status(201).body(user);
	}
	
	@PreAuthorize("hasAnyRole('ADMIN', 'MANAGER') or #id == authentication.principal.user.id")
	@PutMapping(value = "/{id}")
	public ResponseEntity<UserDTO> updateUser(@PathVariable Long id, @Valid @RequestBody UserUpdateDTO dto) {
		UserDTO user = userService.updateUser(id, dto);
		return ResponseEntity.ok(user);
	}
	
	@PreAuthorize("#id == authentication.principal.user.id")
	@PatchMapping(value = "/{id}/password")
	public ResponseEntity<Void> updatePassword(@PathVariable Long id,@RequestBody String newPassword) {
		userService.updatePassword(id, newPassword);
		return ResponseEntity.noContent().build();
	}
	
	@PreAuthorize("hasAnyRole('ADMIN', 'MANAGER') or #id == authentication.principal.user.id")
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
		userService.deleteUser(id);
		return ResponseEntity.noContent().build();
	}

}
