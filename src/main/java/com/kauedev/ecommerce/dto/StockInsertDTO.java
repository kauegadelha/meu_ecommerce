
package com.kauedev.ecommerce.dto;

import jakarta.validation.constraints.NotBlank;

public class StockInsertDTO {
	@NotBlank(message = "nome do estoque é obrigatório!")
	private String name;
	
	public StockInsertDTO(){}

	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}

}
