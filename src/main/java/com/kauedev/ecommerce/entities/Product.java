package com.kauedev.ecommerce.entities;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_product")
public class Product {
	@Id
	private final String barcode;
	
	private String name;
	private BigDecimal price;
	private String shortDescription;
	private final LocalDateTime createdAt;
	private LocalDateTime updatedAt;
	
	protected Product() {
		this.barcode = null;
		this.createdAt = null;
	}
	
	public Product(String barcode, String name, BigDecimal price, String shortDescription) {
		if (barcode == null || barcode.isBlank()) throw new IllegalArgumentException("Código de barra é obrigatório!");
		this.barcode = barcode;
		if (name == null || name.isBlank()) throw new IllegalArgumentException("Nome é obrigatório!");
		this.name = name;
		if (!validatePrice(price)) throw new IllegalArgumentException("Preço inferior ou igual a zero é inválido!");
		this.price = price;
		if (shortDescription == null || shortDescription.isBlank()) throw new IllegalArgumentException("Descrição curta é obrigatória!");
		this.shortDescription = shortDescription;
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
		if (name == null || name.isBlank()) throw new IllegalArgumentException("Nome é obrigatório!");
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
	
	public String getShortDescription() {
		return shortDescription;
	}
	
	public void setShortDescription(String shortDescription) {
		if (shortDescription == null || shortDescription.isBlank()) throw new IllegalArgumentException("Descrição curta é obrigatória!");
		this.shortDescription = shortDescription;
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
		return price != null && price.compareTo(BigDecimal.ZERO) > 0;
	}
	
	@Override
	public String toString() {
		return "Product{name='%s', barcode='%s'}".formatted(name, barcode);
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (!(obj instanceof Product)) return false;
		
		Product other = (Product) obj;
		
		return barcode.equals(other.barcode);
		
	}
	
	@Override 
	public int hashCode() {
		return barcode.hashCode();
	}
	
}
