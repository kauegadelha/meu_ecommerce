package com.kauedev.ecommerce.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kauedev.ecommerce.dto.ProductDTO;
import com.kauedev.ecommerce.dto.ProductInsertDTO;
import com.kauedev.ecommerce.dto.ProductUpdateDTO;
import com.kauedev.ecommerce.entities.Product;
import com.kauedev.ecommerce.repositories.ProductRepository;
import com.kauedev.ecommerce.services.exceptions.ResourceNotFoundException;

@Service
public class ProductService {
	@Autowired
	private ProductRepository productRepository;
	
	private void validateProductExists(String barcode) {
		if (!productRepository.existsById(barcode)) 
			throw new ResourceNotFoundException("Produto não encontrado: %s".formatted(barcode));
	}
	
	
	@Transactional(readOnly = true)
	public List<ProductDTO> findByName(String name) {
		List<Product> products = productRepository.findByName(name);
		return products.stream()
				.map(p -> new ProductDTO(p))
				.toList();
	}
	
	@Transactional
	public ProductDTO insertProduct(ProductInsertDTO dto) {
		Product p = new Product(
				dto.getBarcode(),
				dto.getName(),
				dto.getPrice(),
				dto.getShortDescription()
				);
				
		p = productRepository.save(p);
		return new ProductDTO(p);
	}
	
	@Transactional
	public ProductDTO updateProduct(String barcode, ProductUpdateDTO dto) {
		Product product = productRepository.findById(barcode)
				.orElseThrow(() -> new ResourceNotFoundException("Produto não encontrado: %s".formatted(barcode)));	
		
		product.setName(dto.getName());
		product.setPrice(dto.getPrice());
		product.setShortDescription(dto.getShortDescription());		
		
		product = productRepository.save(product);
		
		return new ProductDTO(product);
	}
	
	@Transactional
	public void deleteProduct(String barcode) {
		validateProductExists(barcode);
		
		productRepository.deleteById(barcode);
	}
	
}
