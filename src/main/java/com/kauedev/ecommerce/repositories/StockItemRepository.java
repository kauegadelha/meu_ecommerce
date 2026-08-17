package com.kauedev.ecommerce.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kauedev.ecommerce.entities.Product;
import com.kauedev.ecommerce.entities.Stock;
import com.kauedev.ecommerce.entities.StockItem;

public interface StockItemRepository extends JpaRepository<StockItem, Long> {
	Optional<StockItem> findByStockAndProduct(Stock stock, Product product);
}
