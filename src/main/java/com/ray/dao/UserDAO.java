package com.ray.dao;

import java.util.List;

import com.ray.entity.User;

public class UserDAO extends JpaDAO<User> {
	
	public UserDAO() {
		super(User.class);
	}

	@Override
	public User insert(User obj) {
		return super.insert(obj);
	}

	@Override
	public User update(User obj) {
		return super.update(obj);
	}

	@Override
	public User findOne(Object objId) {
		return super.findOne(objId);
	}

	@Override
	public List<User> finAll() {
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

	public User getUserByEmail(String email) {
		List<User> userList = super.getNamedQueryWithParam("User.HQL.findByEmail", email);
		
		if(userList != null && userList.size() >= 1) {
			return userList.get(0);
		}
		
		return null;
	}

	
	
}
