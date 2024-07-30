package com.task.ecomart.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.task.ecomart.dto.CategoryDTO;
import com.task.ecomart.service.CategoryService;



@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:4200")
public class CategoryController {

	@Autowired
	private CategoryService categoryService;

	@GetMapping("/categories")
	public ResponseEntity<List<CategoryDTO>> getAllCatgeories(
			@RequestParam(name = "pageNumber", defaultValue = "0") Integer pageNumber,
			@RequestParam(name = "pageSize", defaultValue = "5") Integer pageSize
			) {
		List<CategoryDTO> categoryResponse = this.categoryService.getAllCategories(pageNumber, pageSize);
		return ResponseEntity.status(HttpStatus.OK).body(categoryResponse);
	}

	@GetMapping("/category/{categoryId}")
	public ResponseEntity<CategoryDTO> getCategoryById(@PathVariable Long categoryId) {
		CategoryDTO categoryDTO = this.categoryService.findCategoryById(categoryId);
		return new ResponseEntity<>(categoryDTO, HttpStatus.OK);
	}

	@PostMapping("/category")
	public ResponseEntity<CategoryDTO> creategory(@RequestBody CategoryDTO categoryDTO) {
		CategoryDTO savedCategoryDTO = this.categoryService.createCategory(categoryDTO);
		return new ResponseEntity<>(savedCategoryDTO, HttpStatus.OK);
	}

	@DeleteMapping("/category/{categoryId}")
	public ResponseEntity<CategoryDTO> deleteCategory(@PathVariable Long categoryId) {
		CategoryDTO deletedCategoryDTO = this.categoryService.deleteCategory(categoryId);
		return new ResponseEntity<>(deletedCategoryDTO, HttpStatus.OK);
	}

	@PutMapping("/admin/category/{categoryId}")
	public ResponseEntity<CategoryDTO> updateCategory(@PathVariable Long categoryId,
			 @RequestBody CategoryDTO category) {
		CategoryDTO updateStatus = this.categoryService.updateCategory(categoryId, category);
		return new ResponseEntity<>(updateStatus, HttpStatus.OK);
	}
}
