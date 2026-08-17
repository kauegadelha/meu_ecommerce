package com.kauedev.ecommerce.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.kauedev.ecommerce.entities.Product;

public class ProductDTO {
	private String barcode;
	private String name;
	private BigDecimal price;
	private String shortDescription;
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;
	
	public ProductDTO(Product entity) {
		this.barcode = entity.getBarcode();
		this.name = entity.getName();
		this.price = entity.getPrice();
		this.shortDescription = entity.getShortDescription();
		this.createdAt = entity.getCreatedAt();
		this.updatedAt = entity.getUpdatedAt();
	}
	

	public String getBarcode() {
		return barcode;
	}

	public String getName() {
		return name;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public String getShortDescription() {
		return shortDescription;
	}
	
	public LocalDateTime getCreatedAt() {
		return createdAt;
	}
	
	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}
	
}
