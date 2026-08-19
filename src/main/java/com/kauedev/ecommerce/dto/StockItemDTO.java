package com.kauedev.ecommerce.dto;

import java.math.BigDecimal;

import com.kauedev.ecommerce.entities.StockItem;

public class StockItemDTO {
	private Long stockId;
	private String stockName;
	private String productBarcode;
	private String productName;
	private BigDecimal productPrice;
	private int quantity;

	public StockItemDTO(StockItem entity) {
		this.stockId = entity.getStock().getId();
		this.stockName = entity.getStock().getName();
		this.productBarcode = entity.getProduct().getBarcode();
		this.productName = entity.getProduct().getName();
		this.productPrice = entity.getProduct().getPrice();
		this.quantity = entity.getQuantity();
	}

	public Long getStockId() {
		return stockId;
	}

	public String getStockName() {
		return stockName;
	}

	public String getProductBarcode() {
		return productBarcode;
	}

	public String getProductName() {
		return productName;
	}

	public BigDecimal getProductPrice() {
		return productPrice;
	}

	public int getQuantity() {
		return quantity;
	}
}