package com.ray.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ray.entity.User;

public class UserDAO extends JpaDAO<User> {
	
	public UserDAO() {
		super(User.class);
	}

	@Override
	public User insert(User user) {
		return super.insert(user);
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
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("email", email);
		
		List<User> userList = super.getNamedQueryWithParam("User.HQL.findByEmail", params);
		
		if(userList != null && userList.size() >= 1) {
			return userList.get(0);
		}
		
		return null;
	}
	
	public User getUserByUserId(Integer userId) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("userId", userId);
		
		List<User> userList = super.getNamedQueryWithParam("User.HQL.getUserById", params);
		
		if(userList != null && userList.size() >= 1) {
			return userList.get(0);
		}
		
		return null;
	}

	public User getUserByEmailAndNotId(String email, Integer userId ) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("email", email);
		params.put("userId", userId);
		
		List<User> userList = super.getNamedQueryWithParam("User.HQL.getUserByEmailAndNotId", params);
		
		if(userList != null && userList.size() >= 1) {
			return userList.get(0);
		}
		
		return null;
	}
	
}
