package com.kauedev.ecommerce.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kauedev.ecommerce.dto.ProductDTO;
import com.kauedev.ecommerce.dto.ProductInsertDTO;
import com.kauedev.ecommerce.dto.ProductUpdateDTO;
import com.kauedev.ecommerce.services.ProductService;

import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/product")
public class ProductController {
	@Autowired
	private ProductService productService;
	
	@GetMapping(value = "/name")
	public ResponseEntity<List<ProductDTO>> findByName(@RequestParam String name) {
		List<ProductDTO> p = productService.findByName(name);
		return ResponseEntity.ok(p);
	}
	
	@PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')")
	@PostMapping
	public ResponseEntity<ProductDTO> insertProduct(@Valid @RequestBody ProductInsertDTO dto) {
		ProductDTO p = productService.insertProduct(dto);
		return ResponseEntity.status(201).body(p);
	}
	
	@PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')")
	@PutMapping(value = "/{barcode}")
	public ResponseEntity<ProductDTO> updateProduct(@PathVariable String barcode, @Valid @RequestBody ProductUpdateDTO dto) {
		ProductDTO p = productService.updateProduct(barcode, dto);
		return ResponseEntity.ok(p);
	}
	
	@PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')")
	@DeleteMapping(value = "/{barcode}")
	public ResponseEntity<Void> deleteProduct(@PathVariable String barcode) {
		productService.deleteProduct(barcode);
		return ResponseEntity.noContent().build();
	}

}
