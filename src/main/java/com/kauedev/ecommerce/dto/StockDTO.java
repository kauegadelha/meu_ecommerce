package com.kauedev.ecommerce.dto;

import com.kauedev.ecommerce.entities.Stock;

public class StockDTO {
	private Long id;
	private String name;

	public StockDTO(Stock entity) {
		this.id = entity.getId();
		this.name = entity.getName();
	}

	public Long getId() { return id; }
	public String getName() { return name; }
}