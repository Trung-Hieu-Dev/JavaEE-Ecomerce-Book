package com.ray.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ray.entity.Category;

public class CategoryDAO extends JpaDAO<Category> {
	
	public CategoryDAO() {
		super(Category.class);
	}

	@Override
	public Category insert(Category Category) {
		return super.insert(Category);
	}

	@Override
	public Category update(Category obj) {
		return super.update(obj);
	}

	@Override
	public Category findOne(Object objId) {
		return super.findOne(objId);
	}

	@Override
	public List<Category> finAll() {
		return super.finAll();
	}

	@Override
	public void delete(Object objId) {
		super.delete(objId);
	}

	@Override
	public long getTotalRecord() {
		return super.getTotalRecord();
	}

	public Category getByName(String name) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("name", name);
		
		List<Category> categoryList = super.getNamedQueryWithParam("Category.HQL.getByName", params);
		
		if(categoryList != null && categoryList.size() >= 1) {
			return categoryList.get(0);
		}
		
		return null;
	}
	
	public Category getUserByNameAndNotId(Category category) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("name", category.getName());
		params.put("categoryId", category.getCategoryId());
		
		List<Category> categoryList = super.getNamedQueryWithParam("Category.HQL.getByNameAndNotId", params);
		
		if(categoryList != null && categoryList.size() >= 1) {
			return categoryList.get(0);
		}
		
		return null;
	}
	
}
