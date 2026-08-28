package com.kauedev.ecommerce.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

public class OrderItemInsertDTO {

	@NotBlank(message = "código de barra do produto é obrigatório!")
	private String productBarcode;

	@Positive(message = "quantidade deve ser maior que zero!")
	private int quantity;

	public OrderItemInsertDTO() {}

	public String getProductBarcode() {
		return productBarcode;
	}

	public void setProductBarcode(String productBarcode) {
		this.productBarcode = productBarcode;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
}