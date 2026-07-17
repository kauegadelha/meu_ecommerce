package com.kauedev.ecommerce.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.kauedev.ecommerce.entities.Product;

public interface ProductRepository extends JpaRepository<Product, String> {
	
	@Query(nativeQuery = true, value = """
			SELECT *
			FROM tb_product p
			WHERE p.name = :name 
			""")
	List<Product> findByName(@Param("name") String name);

}
