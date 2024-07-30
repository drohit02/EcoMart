package com.task.ecomart.dto;

public class CategoryDTO {
	private Long categoryId;
	private String categoryName;
	
	public CategoryDTO() {
		// TODO Auto-generated constructor stub
	}
	public CategoryDTO(Long categoryId, String categoryName) {
		super();
		this.categoryId = categoryId;
		this.categoryName = categoryName;
	}
	public Long getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

}
