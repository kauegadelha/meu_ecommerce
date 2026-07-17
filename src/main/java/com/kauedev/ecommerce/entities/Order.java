package com.kauedev.ecommerce.entities;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;


@Entity
@Table(name = "tb_order")
public class Order {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<OrderItem> items;
	
	public enum OrderStatus {
	    DRAFT,
	    SUBMITTED
	}

	@Enumerated(EnumType.STRING)
	private OrderStatus status;
	
	public Order() {
		this.items = new ArrayList<>();
		this.status = OrderStatus.DRAFT;
	}

	public Long getId() {
		return id;
	}
	
	public OrderStatus getStatus() {
		return status;
	}
	
	public void addItem(Product product, int quantity) {
		if (status != OrderStatus.DRAFT) throw new IllegalStateException("Pedido não pode ser editado após ser enviado!");
		
		for (OrderItem item: items) {
			if (item.getProduct().equals(product)) {
				item.increaseQuantity(quantity);
				return;
			}
		}
		items.add(new OrderItem(this, product, quantity));
	}
	
	public boolean removeItem(Product product, int quantity) {
		if (status != OrderStatus.DRAFT) throw new IllegalStateException("Pedido não pode ser editado após ser enviado!");
		
		Iterator<OrderItem> it = items.iterator();
		
		while(it.hasNext()) {
			OrderItem item = it.next();
			
			if (item.getProduct().equals(product)) {
				if (quantity > item.getQuantity()) {
				    throw new IllegalArgumentException(
				        "Quantidade a remover (" + quantity + ") maior que a disponível (" + item.getQuantity() + ")"
				    );
				} else if (quantity == item.getQuantity()) {
				    it.remove();
				} else {
				    item.decreaseQuantity(quantity);
				}
				
				return true;
			}
		}
		return false;
	}
	
	public void submit() {
	    if (items.isEmpty()) throw new IllegalStateException("Pedido vazio não pode ser enviado!");
	    this.status = OrderStatus.SUBMITTED;
	}
	
	public List<OrderItem> getItems(){
		return List.copyOf(items);
	}
	
	@Override
	public boolean equals(Object obj) {
	    if (this == obj) return true;
	    if (!(obj instanceof Order)) return false;
	    Order other = (Order) obj;
	    return id != null && id.equals(other.id);
	}

	@Override
	public int hashCode() {
	    return getClass().hashCode(); 
	}
}
