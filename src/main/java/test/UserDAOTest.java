package test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Objects;

import org.hibernate.AssertionFailure;
//import org.junit.BeforeClass;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.ray.dao.UserDAO;
import com.ray.entity.User;
import com.ray.service.UserService;

class UserDAOTest {
	private UserDAO userDAO;
	private UserService userService;
	
	@BeforeEach
	public void initUserDAO() {
		this.userDAO = new UserDAO();
		this.userService = new UserService();
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
		User user = new User(9, "ray@email.com", "12345", "John");
		String errorMessage = this.userService.updateUser(user);
		assertNotNull(errorMessage);
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
	
	@Test
	void testGetUserByEmail() {
		User user = userDAO.getUserByEmail("ray@email.com");
		assertNotNull(user);
	}
	

}
