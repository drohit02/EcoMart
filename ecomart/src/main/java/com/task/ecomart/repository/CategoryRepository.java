package com.task.ecomart.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.task.ecomart.models.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long>{

	Optional<Category> findByCategoryName(String categoryName);

}
