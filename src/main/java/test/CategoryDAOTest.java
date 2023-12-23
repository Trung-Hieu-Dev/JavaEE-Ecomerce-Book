package test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.Objects;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.ray.dao.CategoryDAO;
import com.ray.entity.Category;


class CategoryDAOTest {

	private CategoryDAO categoryDAO;
	
	@BeforeEach
	public void initCategoryDAO() {
		this.categoryDAO = new CategoryDAO();
	}

	@Test
	void testGetTotalRecord() {
		fail("Not yet implemented");
	}

	@Test
	void testInsertCategory() {
		Category category = new Category("Test Create");
		Category insertedCategory = categoryDAO.insert(category);
		
		// if success must have id quantity
		assertTrue(insertedCategory.getCategoryId() > 0);
	}

	@Test
	void testUpdateCategory() {
		Category category = new Category("Test Update");
		Category insertedCategory = categoryDAO.insert(category);
		Integer insertedCategoryId = insertedCategory.getCategoryId();
		Category existedCategory = categoryDAO.findOne(insertedCategoryId);
		existedCategory.setName("New Updated");
		Category updatedCategory = categoryDAO.update(existedCategory);
				
		Category expectedCategory = new Category(insertedCategoryId, "New Updated");
		
		assertEquals(expectedCategory, updatedCategory);
	}

	@Test
	void testFindOneObject() {
		Category category = new Category("Test Find One");
		Category insertedCategory = categoryDAO.insert(category);
		Integer insertedCategoryId = insertedCategory.getCategoryId();
		Category existedCategory = categoryDAO.findOne(insertedCategoryId);
		
		assertNotNull(existedCategory);
	}

	@Test
	void testFinAll() {
		List<Category> categories = categoryDAO.finAll();
		assertTrue(categories.size() > 0);
	}

	@Test
	void testDeleteCategory() {
		Category category = new Category("Test");
		Category insertedCategory = categoryDAO.insert(category);
		Integer insertedCategoryId = insertedCategory.getCategoryId();
		
		categoryDAO.delete(insertedCategoryId);
		assertTrue(Objects.isNull(categoryDAO.findOne(insertedCategoryId)));
	}

}
