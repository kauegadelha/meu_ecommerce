package com.kauedev.ecommerce.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kauedev.ecommerce.dto.StockDTO;
import com.kauedev.ecommerce.dto.StockInsertDTO;
import com.kauedev.ecommerce.services.StockService;

import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/stock")
public class StockController {
	
	@Autowired
	private StockService stockService;
	
	@PostMapping(value = "/create")
	public ResponseEntity<StockDTO> insertStock(@Valid @RequestBody StockInsertDTO dto){
		StockDTO stock = stockService.insertStock(dto);
		return ResponseEntity.status(201).body(stock);
	}
	
}
