package com.kauedev.ecommerce.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.kauedev.ecommerce.entities.User;

public interface UserRepository extends JpaRepository<User, Long> {
	
	@Query(nativeQuery = true, value  = """
			SELECT * FROM tb_user u
			WHERE u.user_name = :name 
			""")
	Optional<User> findByName(@Param("name") String name);
}
