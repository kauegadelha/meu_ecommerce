package com.kauedev.ecommerce.entities;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Product {
	private final String barcode;
	private String name;
	private BigDecimal price;
	private final LocalDateTime createdAt;
	private LocalDateTime updatedAt;
	
	public Product(String barcode, String name, BigDecimal price) {
		this.barcode = barcode;
		this.name = name;
		if (!validatePrice(price)) throw new IllegalArgumentException("Preço inferior ou igual a zero é inválido!");
		this.price = price;
		this.createdAt = LocalDateTime.now();
		this.updatedAt = createdAt;
	}
	public String getBarcode() {
		return barcode;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
		updateTimestamp();
	}
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		if (!validatePrice(price)) throw new IllegalArgumentException("Preço inferior ou igual a zero é inválido!");
		this.price = price;
		updateTimestamp();
	}
	
	public LocalDateTime getCreatedAt() {
		return createdAt;
	}
	
	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}
	
	private void updateTimestamp() {
		this.updatedAt = LocalDateTime.now();
	}
	
	private boolean validatePrice(BigDecimal price) {
		if (price == null || price.compareTo(BigDecimal.ZERO) <= 0) {
			return false;
		}
		return true;
	}
}
