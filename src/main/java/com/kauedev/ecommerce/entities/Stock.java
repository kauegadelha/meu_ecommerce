package com.kauedev.ecommerce.entities;

import java.util.HashMap;
import java.util.Map;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MapKeyJoinColumn;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_stock")
public class Stock {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String name;
	
	@ElementCollection
	@CollectionTable(
			name = "tb_stock_product",
			joinColumns = @JoinColumn(name = "stock_id")
	)
	@MapKeyJoinColumn(name = "product_barcode")
	@Column(name = "quantity")
	private Map<Product, Integer> products;
	
	protected Stock() {
	}
	
	public Stock(String name) {
		if (name == null || name.isBlank()) throw new IllegalArgumentException("Nome é obrigatório!");
		this.name = name;
		products = new HashMap<>();
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		if (name == null || name.isBlank()) throw new IllegalArgumentException("Nome é obrigatório!");
		this.name = name;
	}

	public void addProduct(Product product, int quantity) {
		if (product == null)throw new IllegalArgumentException("Produto é obrigatório!");
		if (quantity <= 0) throw new IllegalArgumentException("Quantidade deve ser maior que zero!");
		
		products.put(product, products.getOrDefault(product, 0) + quantity);
	}
	
	public boolean removeProduct(Product product, int quantity) {
		if (!products.containsKey(product)) return false;
		int current = products.get(product);
		
		if (quantity > current) return false;
		
		if (quantity == current) {
			products.remove(product);
		} else {
			products.put(product, current - quantity);
		}
		return true;
	}
	
	public int getQuantity(Product product){
		if (product == null) throw new IllegalArgumentException("Produto é obrigatório!");
		
		return products.getOrDefault(product, 0);
	}
	
	public boolean hasAvailableQuantity(Product product, int quantity) {
		if (product == null) throw new IllegalArgumentException("Produto é obrigatório!");
		if (quantity <= 0) throw new IllegalArgumentException("Quantidade deve ser positiva!");
		
		return quantity <= getQuantity(product);
	}
	
	@Override
	public String toString() {
		return """
				Stock{
				id='%d', 
				name='%s', 
				products='%s'}
				""".formatted(id, name, products);
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (!(obj instanceof Stock)) return false;
		
		Stock other = (Stock) obj;
		return name.equals(other.name);
	}
	
	@Override
	public int hashCode() {
		return name.hashCode();
	}
}
