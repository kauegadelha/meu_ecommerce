package com.kauedev.ecommerce.entities;

import java.math.BigDecimal;
import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_order_item")
public class OrderItem {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "order_id",nullable = false)
	private Order order;
	
	@ManyToOne
	@JoinColumn(name = "product_barcode", nullable = false)
	private Product product;
	
	private int quantity;
	private BigDecimal priceAtPurchase;
	
	protected OrderItem() {
	}
	
	public OrderItem(Order order, Product product, int quantity) {
		if (order == null) throw new IllegalArgumentException("Pedido é obrigatório!");
		this.order = order;
		
		if (product == null) throw new IllegalArgumentException("Produto é obrigatório!");
		this.product = product;
		
        if (quantity <= 0) throw new IllegalArgumentException("Quantidade deve ser positiva!");
		this.quantity = quantity;
		
		this.priceAtPurchase = product.getPrice();
	}
	
	public Long getId() {
		return id;
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
	
	public BigDecimal getSubtotal() {
		return priceAtPurchase.multiply(BigDecimal.valueOf(quantity));
	}
	
	@Override
	public boolean equals(Object obj) {
	    if (this == obj) return true;
	    if (!(obj instanceof OrderItem)) return false;
	    OrderItem other = (OrderItem) obj;
	    return order.equals(other.order) && product.equals(other.product);
	}

	@Override
	public int hashCode() {
	    return Objects.hash(order, product);
	}
	
}
