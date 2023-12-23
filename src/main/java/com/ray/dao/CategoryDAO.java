package com.ray.dao;

import java.util.List;

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

	
}
