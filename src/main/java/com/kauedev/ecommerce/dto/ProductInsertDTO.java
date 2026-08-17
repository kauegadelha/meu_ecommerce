package com.kauedev.ecommerce.dto;

import java.math.BigDecimal;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class ProductInsertDTO {
	@NotBlank(message = "código de barra é obrigatório!")
	private String barcode;
	
	@NotBlank(message = "nome do produto é obrigatório!")
	private String name;
	
	@NotNull(message = "Preço é obrigatório!")
	@Positive(message = "Preço deve ser maior que zero!")
	private BigDecimal price;
	
	private String shortDescription;
	
	public ProductInsertDTO(){}

	public String getBarcode() {
		return barcode;
	}

	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public String getShortDescription() {
		return shortDescription;
	}

	public void setShortDescription(String shortDescription) {
		this.shortDescription = shortDescription;
	}

}
