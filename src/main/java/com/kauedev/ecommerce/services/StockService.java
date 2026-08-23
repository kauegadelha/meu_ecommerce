package com.kauedev.ecommerce.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kauedev.ecommerce.dto.StockDTO;
import com.kauedev.ecommerce.dto.StockInsertDTO;
import com.kauedev.ecommerce.entities.Stock;
import com.kauedev.ecommerce.repositories.StockItemRepository;
import com.kauedev.ecommerce.repositories.StockRepository;
import com.kauedev.ecommerce.services.exceptions.ResourceNotFoundException;

@Service
public class StockService {
	@Autowired
	private StockRepository stockRepository;
	
	@Autowired
	private StockItemRepository stockItemRepository;
	
	@Transactional(readOnly = true)
	public StockDTO findByName(String name) {
		Stock stock = stockRepository.findByName(name)
			.orElseThrow(() -> new ResourceNotFoundException("Estoque não encontrado: %s".formatted(name)));
		return new StockDTO(stock);
	}
	
	@Transactional
	public StockDTO insertStock(StockInsertDTO dto) {
		Stock stock = new Stock(dto.getName());
		stock = stockRepository.save(stock);
		return new StockDTO(stock);
	}
	
	@Transactional
	public StockDTO updateStock(Long id, StockInsertDTO dto) {
		Stock stock = stockRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Estoque não encontrado, id: %d".formatted(id)));

		stock.setName(dto.getName());
		stock = stockRepository.save(stock);

		return new StockDTO(stock);
	}
	
	@Transactional
	public void deleteStock(Long id) {
		Stock stock = stockRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Estoque não encontrado, id: %id".formatted(id)));
		
		if (!stockItemRepository.findById(id).isEmpty()) {
			throw new IllegalStateException("Não é possivel excluir um estoque que possui itens associados!");
		}
		
		stockRepository.delete(stock);
	}

}
