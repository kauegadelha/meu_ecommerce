package com.kauedev.ecommerce.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
	
	@GetMapping(value = "/name")
	public ResponseEntity<StockDTO> findByName(@RequestParam String name){
		StockDTO stock = stockService.findByName(name);
		return ResponseEntity.ok(stock);
	}

	@PostMapping
	public ResponseEntity<StockDTO> insertStock(@Valid @RequestBody StockInsertDTO dto){
		StockDTO stock = stockService.insertStock(dto);
		return ResponseEntity.status(201).body(stock);
	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<StockDTO> updateStock(@PathVariable Long id, @Valid @RequestBody StockInsertDTO dto) {
		StockDTO stock = stockService.updateStock(id, dto);
		return ResponseEntity.ok(stock);
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> deleteStock(@PathVariable Long id) {
		stockService.deleteStock(id);
		return ResponseEntity.noContent().build();
	}

}