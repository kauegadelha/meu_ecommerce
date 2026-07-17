package com.kauedev.ecommerce.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kauedev.ecommerce.entities.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {

}
