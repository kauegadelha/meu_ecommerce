package com.kauedev.ecommerce.entities;
import java.util.ArrayList;
import java.util.List;

public class Order {
	private final Long id;
	private List<OrderItem> items;
	
	public Order(Long id) {
		this.id = id;
		this.items = new ArrayList<>();
	}

	public Long getId() {
		return id;
	}
	
	public void addItem(Product product, int quantity) {
		for (OrderItem item: items) {
			if (item.getProduct().equals(product)) {
				item.increaseQuantity(quantity);
				return;
			}
		}
		items.add(new OrderItem(product, quantity));
	}
	
	public boolean removeItem(Product product, int quantity) {
		for (OrderItem item: items) {
			if (item.getProduct().equals(product)) {
				
				if (quantity >= item.getQuantity()) {
	                items.remove(item);
	            } else {
	                item.decreaseQuantity(quantity);
	            }
				
				return true;
			}
		}
		return false;
	}
	
	public List<OrderItem> getItems(){
		return List.copyOf(items);
	}
}
