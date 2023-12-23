package com.ray.service;

import java.util.List;

import com.ray.dao.CategoryDAO;
import com.ray.entity.Category;

public class CategoryService {
	private CategoryDAO categoryDAO;

	public CategoryService() {
		this.categoryDAO = new CategoryDAO();
	}
	
	public List<Category> listCategory() {
		return categoryDAO.finAll();
	}
	
	public Category getById(Integer categoryId) {
		return categoryDAO.findOne(categoryId);
	}
	
	public void delete(Integer categoryId) {
		categoryDAO.delete(categoryId);
	}
	
	public String create(Category category) {
		Category existedCategory = categoryDAO.getByName(category.getName());
		
		if (existedCategory != null) {
			return "Category name is existed. Please choose another name.";
		}
		categoryDAO.insert(category);
		
		return null;
	}
	
	public String update(Category category) {
		Category existedCategory = categoryDAO.getUserByNameAndNotId(category);
		
		if (existedCategory != null) {
			return "Category was existed. Please choose another name.";
		}
		categoryDAO.update(category);
		
		return null;
	}
}
