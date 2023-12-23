package com.ray.service;

import com.ray.dao.UserDAO;
import com.ray.entity.User;

public class UserService {
	private UserDAO userDAO;

	public UserService() {
		this.userDAO = new UserDAO();
	}
	
	public String createUser(User user) {
		User existedUser = userDAO.getUserByEmail(user.getEmail());
		
		if (existedUser != null) {
			return "Email is existed. Please choose another one.";
		}
		
		userDAO.insert(user);
		
		return null;
	}
	
	public User getUserById(Integer userId) {
		return this.userDAO.getUserByUserId(userId);
	}
	
	public String updateUser(User user) {
		User existedUser = userDAO.getUserByEmailAndNotId(user.getEmail(), user.getUserId());
		
		if (existedUser != null) {
			return "Email is existed. Please choose another one.";
		}
		
		userDAO.update(user);
		return null;
	}
	
	public void deleteUser(Integer userId) {
		this.userDAO.delete(userId);
	}
	
}
