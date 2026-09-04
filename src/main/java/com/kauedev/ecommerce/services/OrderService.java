package com.kauedev.ecommerce.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kauedev.ecommerce.dto.OrderDTO;
import com.kauedev.ecommerce.dto.OrderItemInsertDTO;
import com.kauedev.ecommerce.entities.Order;
import com.kauedev.ecommerce.entities.Product;
import com.kauedev.ecommerce.entities.User;
import com.kauedev.ecommerce.repositories.OrderRepository;
import com.kauedev.ecommerce.repositories.ProductRepository;
import com.kauedev.ecommerce.repositories.StockItemRepository;
import com.kauedev.ecommerce.services.exceptions.ResourceNotFoundException;

@Service
public class OrderService {
	
	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private StockItemRepository stockItemRepositoy;
	
	public void validateOwnership(Order order, User user) {
		if (!order.getUser().getId().equals(user.getId())) {
			throw new AccessDeniedException("Você não tem permissão para acessar esse pedido!");
		}
	}
	
	@Transactional
	public OrderDTO insertOrder(User user, OrderItemInsertDTO dto) {
		Product product = productRepository.findById(dto.getProductBarcode())
			.orElseThrow(() -> new ResourceNotFoundException("Produto não encontrado: %s".formatted(dto.getProductBarcode())));
		Order order = new Order(user);
		
		order.addItem(product, dto.getQuantity());
		
		order = orderRepository.save(order);
		return new OrderDTO(order);
	}
	
	@Transactional 
	public OrderDTO addItem(Long orderId, User user, OrderItemInsertDTO dto) {
		Order order = orderRepository.findById(orderId)
				.orElseThrow(() -> new ResourceNotFoundException("Pedido não encontrado: %d".formatted(orderId)));
		validateOwnership(order, user);
		
		Product product = productRepository.findById(dto.getProductBarcode())
				.orElseThrow(() -> new ResourceNotFoundException("Produto não encontrado: %s".formatted(dto.getProductBarcode())));
		
		order.addItem(product, dto.getQuantity());
		order = orderRepository.save(order);
		
		return new OrderDTO(order);
	}
	
	@Transactional
	public OrderDTO removeItem(Long orderId, User user, String barcode, int quantity) {
		Order order = orderRepository.findById(orderId)
				.orElseThrow(() -> new ResourceNotFoundException("Pedido não encontrado: %d".formatted(orderId)));
		validateOwnership(order, user);
		
		Product product = productRepository.findById(barcode)
				.orElseThrow(() -> new ResourceNotFoundException("Produto não encontrado: %s".formatted(barcode)));
		
		if(!order.removeItem(product, quantity)) {
			throw new ResourceNotFoundException("Produto não encontrado neste pedido: %s".formatted(barcode));
		}
		order = orderRepository.save(order);
		
		return new OrderDTO(order);
	}

}
