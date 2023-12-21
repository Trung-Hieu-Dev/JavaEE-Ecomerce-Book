package com.ray.dao;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Objects;

//import org.junit.BeforeClass;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.ray.entity.User;

class UserDAOTest {
	private UserDAO userDAO;
	
	@BeforeEach
	public void initUserDAO() {
		this.userDAO = new UserDAO();
	}

	@Test
	void testGetTotalRecord() {
		fail("Not yet implemented");
	}

	@Test
	void testInsertUser() {
		User user = new User("test@mail.com", "12345", "John");
		User insertedUser = userDAO.insert(user);
		
		// if success must have id quantity
		assertTrue(insertedUser.getUserId() > 0);
	}

	@Test
	void testUpdateUser() {
		fail("Not yet implemented");
	}

	@Test
	void testFindOneObject() {
		fail("Not yet implemented");
	}

	@Test
	void testFinAll() {
		fail("Not yet implemented");
	}

	@Test
	void testDeleteUser() {
		userDAO.delete(13);
		
		assertTrue(Objects.isNull(userDAO.findOne(13)));
	}

}
