package com.kauedev.ecommerce.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kauedev.ecommerce.dto.StockItemDTO;
import com.kauedev.ecommerce.dto.StockItemInsertDTO;
import com.kauedev.ecommerce.entities.Product;
import com.kauedev.ecommerce.entities.Stock;
import com.kauedev.ecommerce.entities.StockItem;
import com.kauedev.ecommerce.repositories.ProductRepository;
import com.kauedev.ecommerce.repositories.StockItemRepository;
import com.kauedev.ecommerce.repositories.StockRepository;
import com.kauedev.ecommerce.services.exceptions.ResourceNotFoundException;

@Service
public class StockItemService {
	
	@Autowired
	private StockItemRepository stockItemRepository;
	
	@Autowired
	private StockRepository stockRepository;
	
	@Autowired
	private ProductRepository productRepository;
	
	@Transactional
	public StockItemDTO insertOrIncrementItem(Long stockId, StockItemInsertDTO dto) {
		Stock stock = stockRepository.findById(stockId)
				.orElseThrow(() -> new ResourceNotFoundException("Estoque não encontrado, id: %d".formatted(stockId)));

		Product product = productRepository.findById(dto.getProductBarcode())
				.orElseThrow(() -> new ResourceNotFoundException("Produto não encontrado: %s".formatted(dto.getProductBarcode())));

		StockItem item = stockItemRepository.findByStockAndProduct(stock, product)
				.orElseGet(() -> new StockItem(stock, product, 0));

		item.addQuantity(dto.getQuantity());
		item = stockItemRepository.save(item);

		return new StockItemDTO(item);
	}
}
