package com.task.ecomart.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.task.ecomart.models.Category;
import com.task.ecomart.models.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>{

	Page<Product> findAllByCategory(Category category, Pageable pageData);

}
