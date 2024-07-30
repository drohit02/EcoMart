package com.task.ecomart.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.task.ecomart.customexception.APIException;
import com.task.ecomart.customexception.ResourceNotFoundException;
import com.task.ecomart.dto.CategoryDTO;
import com.task.ecomart.models.Category;
import com.task.ecomart.repository.CategoryRepository;

@Service
public class CategoryService {
	
	@Autowired
	private CategoryRepository categoryRepository;

	public List<CategoryDTO> getAllCategories(Integer pageNumber, Integer pageSize) {
	    /* Pagination logic */
	    Pageable pageData = PageRequest.of(pageNumber, pageSize);
	    Page<Category> categoryPage = this.categoryRepository.findAll(pageData);
	    List<Category> categories = categoryPage.getContent();

	    if (categories.isEmpty()) {
	        throw new APIException("No category is present");
	    }
	    List<CategoryDTO> categoryDTOs = categories.stream()
	            .map(category -> {
	                CategoryDTO dto = new CategoryDTO();
	                dto.setCategoryId(category.getCategoryId());        
	                dto.setCategoryName(category.getCategoryName());
	                return dto;
	            })
	            .collect(Collectors.toList());

	    return categoryDTOs;
	}

	public CategoryDTO createCategory(CategoryDTO categoryDTO) {
	    Optional<Category> existingCategory = this.categoryRepository.findByCategoryName(categoryDTO.getCategoryName());
	    if (existingCategory.isPresent()) {
	        throw new APIException("Category already present!!");
	    }

	    Category category = new Category();
	    category.setCategoryName(categoryDTO.getCategoryName());
	    category = this.categoryRepository.save(category);

	    categoryDTO.setCategoryId(category.getCategoryId());
	    return categoryDTO;
	}

	public CategoryDTO deleteCategory(Long categoryId) {
	    Optional<Category> findCategory = this.categoryRepository.findById(categoryId);
	    Category savedCategory = findCategory
	            .orElseThrow(() -> new ResourceNotFoundException("Category", "categoryId", categoryId));

	    this.categoryRepository.delete(savedCategory);

	    CategoryDTO deleteCategoryDTO = new CategoryDTO();
	    deleteCategoryDTO.setCategoryId(savedCategory.getCategoryId());
	    deleteCategoryDTO.setCategoryName(savedCategory.getCategoryName());
	    return deleteCategoryDTO;
	}

	public CategoryDTO updateCategory(Long categoryId,CategoryDTO categoryDTO) {
	    Optional<Category> findCategory = this.categoryRepository.findById(categoryId);

	    Category updateCategory = findCategory
	            .orElseThrow(() -> new ResourceNotFoundException("Category", "categoryId", categoryId));

	    updateCategory.setCategoryName(categoryDTO.getCategoryName());
	    updateCategory = this.categoryRepository.save(updateCategory);

	    CategoryDTO updatedCategoryDTO = new CategoryDTO();
	    updatedCategoryDTO.setCategoryId(updateCategory.getCategoryId());
	    updatedCategoryDTO.setCategoryName(updateCategory.getCategoryName());
	    return updatedCategoryDTO;
	}

	public CategoryDTO findCategoryById(Long categoryId) {
	    Category category = this.categoryRepository.findById(categoryId)
	            .orElseThrow(() -> new ResourceNotFoundException("Category", "categoryId", categoryId));

	    CategoryDTO categoryDTO = new CategoryDTO();
	    categoryDTO.setCategoryId(category.getCategoryId());
	    categoryDTO.setCategoryName(category.getCategoryName());
	    return categoryDTO;
	}


}
