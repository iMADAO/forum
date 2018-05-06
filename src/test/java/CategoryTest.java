import java.util.List;

import junit.framework.Assert;

import org.junit.Test;

import com.project.bean.Category;
import com.project.dao.CategoryDAO;
import com.project.dao.impl.CategoryDAOImpl;


public class CategoryTest {
	
	private CategoryDAO categoryDAO = new CategoryDAOImpl();
	
	@Test
	public void testAdd(){
		Category category = new Category("1004", "1001", "Java");
		categoryDAO.addCategory(category);	
	}
	
	@Test 
	public void testGet(){
		Category category = categoryDAO.getCategoryById("1001");
		System.out.println(category);
		Assert.assertNotNull(category);
	}
	
	@Test
	public void testGetForList(){
		List<Category> categoryList = categoryDAO.getCategoryByThemeId("1001");
		System.out.println(categoryList.size());
		for(Category category: categoryList){
			System.out.println(category);
		}
	}
	
	@Test
	public void testDeleteById(){
		categoryDAO.deleteCategory("1001");
	}
	
	@Test
	public void testDeleteByThemeId(){
		categoryDAO.deleteCategoryByThemeId("1001");
	}
}
