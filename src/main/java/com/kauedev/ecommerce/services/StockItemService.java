package com.kauedev.ecommerce.services;

import java.util.List;

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
	
	@Transactional
	public void deleteOrDecrementItem(Long stockId, String barcode, int amount) {
		Stock stock = stockRepository.findById(stockId)
				.orElseThrow(() -> new ResourceNotFoundException("Estoque não encontrado, id: %d".formatted(stockId)));

		Product product = productRepository.findById(barcode)
				.orElseThrow(() -> new ResourceNotFoundException("Produto não encontrado: %s".formatted(barcode)));

		StockItem item = stockItemRepository.findByStockAndProduct(stock, product)
				.orElseThrow(() -> new ResourceNotFoundException("Item não encontrado nesse estoque"));
		
		if (item.getQuantity() == amount) {
			stockItemRepository.deleteById(item.getId());
			return;
		}
		
		if (!item.removeQuantity(amount)) {
			throw new IllegalArgumentException("Quantidade acima da disponível no estoque!");
		}
		
		stockItemRepository.save(item);
		
	}
	
	@Transactional(readOnly = true)
	public List<StockItemDTO> findItemsByStockId(Long stockId){
		if(!stockRepository.existsById(stockId)) {
			throw new ResourceNotFoundException("Estoque não encontrado, id: %d".formatted(stockId));
		}
		
		List<StockItem> items = stockItemRepository.findByStockId(stockId);
		
		return items.stream()
				.map(i -> new StockItemDTO(i))
				.toList();
	}
}
