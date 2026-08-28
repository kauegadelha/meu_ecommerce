package com.kauedev.ecommerce.dto;

import java.util.List;

import com.kauedev.ecommerce.entities.Order;
import com.kauedev.ecommerce.entities.Order.OrderStatus;

public class OrderDTO {
	private Long id;
	private Long userId;
	private List<OrderItemDTO> items;
	private OrderStatus status;
	
	public OrderDTO(Order entity){
		this.id = entity.getId();
		this.userId = entity.getUser().getId();
		this.items = entity.getItems().stream()
				.map(OrderItemDTO::new)
				.toList();
		this.status = entity.getStatus();
	}

	public Long getId() {
		return id;
	}

	public Long getUserId() {
		return userId;
	}

	public List<OrderItemDTO> getItems() {
		return items;
	}
	
	public OrderStatus getStatus() {
		return status;
	}
	
}
