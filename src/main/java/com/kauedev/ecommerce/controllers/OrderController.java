package com.kauedev.ecommerce.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kauedev.ecommerce.dto.OrderDTO;
import com.kauedev.ecommerce.dto.OrderItemInsertDTO;
import com.kauedev.ecommerce.security.UserPrincipal;
import com.kauedev.ecommerce.services.OrderService;

import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/order")
public class OrderController {
	@Autowired
	private OrderService orderService;
	
	@PostMapping
	public ResponseEntity<OrderDTO> insertOrder(@AuthenticationPrincipal UserPrincipal userPrincipal , @Valid @RequestBody OrderItemInsertDTO dto){
		OrderDTO order = orderService.insertOrder(userPrincipal.getUser(), dto);
		return ResponseEntity.status(201).body(order);
	}
}
