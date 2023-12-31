package com.ray.entity;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="category")
@NamedQueries({
	@NamedQuery(name="Category.HQL.getByName", 
		query = "SELECT c FROM Category c where c.name = :name"),
	@NamedQuery(name="Category.HQL.getByNameAndNotCategoryId", 
		query = "SELECT c FROM Category c where c.name = :name and c.categoryId != :categoryId")
})
public class Category {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="category_id")
	private Integer categoryId;
	
	@Column(name="name")
	private String name;

	@OneToMany(mappedBy="category")
	private Set<Product> products;
	
	public Category() {
	}

	
	public Category(String name) {
		this.name = name;
	}


	public Category(Integer categoryId, String name) {
		this.categoryId = categoryId;
		this.name = name;
	}

	public Integer getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((categoryId == null) ? 0 : categoryId.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Category other = (Category) obj;
		if (categoryId == null) {
			if (other.categoryId != null)
				return false;
		} else if (!categoryId.equals(other.categoryId))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}


	@Override
	public String toString() {
		return "Category [categoryId=" + categoryId + ", name=" + name + "]";
	}
	
}
