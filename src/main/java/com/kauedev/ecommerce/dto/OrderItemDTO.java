package com.kauedev.ecommerce.dto;

import java.math.BigDecimal;

import com.kauedev.ecommerce.entities.OrderItem;

public class OrderItemDTO {
	private String productBarcode;
	private String productName;
	private int quantity;
	private BigDecimal priceAtPurchase;
	private BigDecimal subtotal;
	
	public OrderItemDTO(OrderItem item) {
		this.productBarcode = item.getProduct().getBarcode();
		this.productName = item.getProduct().getName();
		this.quantity = item.getQuantity();
		this.priceAtPurchase = item.getPriceAtPurchase();
		this.subtotal = item.getSubtotal();
	}

	public String getProductBarcode() {
		return productBarcode;
	}

	public String getProductName() {
		return productName;
	}

	public int getQuantity() {
		return quantity;
	}

	public BigDecimal getPriceAtPurchase() {
		return priceAtPurchase;
	}

	public BigDecimal getSubtotal() {
		return subtotal;
	}
	
	
}
