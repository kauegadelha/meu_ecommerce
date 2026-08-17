package com.kauedev.ecommerce.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

@Entity
@Table(name = "tb_stock_item", uniqueConstraints = @UniqueConstraint(columnNames = {"stock_id", "product_barcode"}))
public class StockItem {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "stock_id", nullable = false)
	private Stock stock;

	@ManyToOne
	@JoinColumn(name = "product_barcode", nullable = false)
	private Product product;

	private int quantity;

	protected StockItem() {
	}

	public StockItem(Stock stock, Product product, int quantity) {
		if (stock == null) throw new IllegalArgumentException("Estoque é obrigatório!");
		this.stock = stock;
		if (product == null) throw new IllegalArgumentException("Produto é obrigatório!");
		this.product = product;
		if (quantity < 0) throw new IllegalArgumentException("Quantidade não pode ser negativa!");
		this.quantity = quantity;
	}

	public Long getId() {
		return id;
	}

	public Stock getStock() {
		return stock;
	}

	public Product getProduct() {
		return product;
	}

	public int getQuantity() {
		return quantity;
	}

	public void addQuantity(int amount) {
		if (amount <= 0) throw new IllegalArgumentException("Quantidade deve ser positiva!");
		this.quantity += amount;
	}

	public boolean removeQuantity(int amount) {
		if (amount <= 0) throw new IllegalArgumentException("Quantidade deve ser positiva!");
		if (amount > quantity) return false;
		this.quantity -= amount;
		return true;
	}

	public boolean hasAvailableQuantity(int amount) {
		if (amount <= 0) throw new IllegalArgumentException("Quantidade deve ser positiva!");
		return amount <= quantity;
	}

	@Override
	public String toString() {
		return "StockItem{stock='%s', product='%s', quantity=%d}".formatted(stock.getName(), product.getName(), quantity);
	}
}