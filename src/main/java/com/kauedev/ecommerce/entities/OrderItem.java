package com.kauedev.ecommerce.entities;

import java.math.BigDecimal;

public class OrderItem {
	private final Product product;
	private int quantity;
	private BigDecimal priceAtPurchase;
	
	public OrderItem(Product product, int quantity) {
		if (product == null) throw new IllegalArgumentException("Produto é obrigatório!");
        if (quantity <= 0) throw new IllegalArgumentException("Quantidade deve ser positiva!");
		
		this.product = product;
		this.quantity = quantity;
		this.priceAtPurchase = product.getPrice();
	}

	public Product getProduct() {
		return product;
	}

	public int getQuantity() {
		return quantity;
	}
	
	 public BigDecimal getPriceAtPurchase() {
	    return priceAtPurchase;
	 }

	
	public void increaseQuantity(int amount) {
		if (amount <= 0) throw new IllegalArgumentException("Quantidade Inválida");
		
		this.quantity += amount;
	}

	public void decreaseQuantity(int amount) {
		if (amount <= 0) throw new IllegalArgumentException("Quantidade Inválida");
		if (amount > quantity) throw new IllegalArgumentException("Quantidade maior que a existente!");
		
		this.quantity -= amount;
	}
	
	

}
