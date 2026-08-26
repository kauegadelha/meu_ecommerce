package com.kauedev.ecommerce.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kauedev.ecommerce.dto.StockItemDTO;
import com.kauedev.ecommerce.dto.StockItemInsertDTO;
import com.kauedev.ecommerce.services.StockItemService;

import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/stock/{stockId}/items")
public class StockItemController {

	@Autowired
	private StockItemService stockItemService;

	@PostMapping
	public ResponseEntity<StockItemDTO> insertOrIncrementItem(@PathVariable Long stockId, @Valid @RequestBody StockItemInsertDTO dto) {
		StockItemDTO item = stockItemService.insertOrIncrementItem(stockId, dto);
		return ResponseEntity.status(201).body(item);
	}
	
	@DeleteMapping(value = "/{barcode}")
	public ResponseEntity<Void> deleteOrDecrementItem(
			@PathVariable Long stockId,
			@PathVariable String barcode,
			@RequestParam int amount) {
		stockItemService.deleteOrDecrementItem(stockId, barcode, amount);
		return ResponseEntity.noContent().build();
	}
	
	@GetMapping
	public ResponseEntity<List<StockItemDTO>> findItemsByStockId(@PathVariable Long stockId){
		List<StockItemDTO> items = stockItemService.findItemsByStockId(stockId);
		return ResponseEntity.ok(items);
	}

}