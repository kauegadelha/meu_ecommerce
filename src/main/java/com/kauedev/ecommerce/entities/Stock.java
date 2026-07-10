package com.kauedev.ecommerce.entities;

import java.util.HashMap;
import java.util.Map;

public class Stock {
	private final Long id;
	private String name;
	private Map<Product, Integer> products;
	
	public Stock(Long id, String name) {
		if (id == null) throw new IllegalArgumentException("Id é obrigatório!");
		this.id = id;
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
		return id.equals(other.id);
	}
	
	@Override
	public int hashCode() {
		return id.hashCode();
	}
}
