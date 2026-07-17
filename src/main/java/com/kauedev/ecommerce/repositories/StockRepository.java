package com.kauedev.ecommerce.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.kauedev.ecommerce.entities.Stock;

public interface StockRepository extends JpaRepository<Stock, Long> {
	@Query(nativeQuery = true, value = """
			SELECT *
			FROM tb_stock s
			WHERE s.name = :name
			""")
	Optional<Stock> findByName(@Param("name") String name);
}
