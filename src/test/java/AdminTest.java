import org.junit.Test;

import com.project.dao.AdminDAO;
import com.project.dao.impl.AdminDAOImpl;


public class AdminTest {
	private AdminDAO adminDAO = new AdminDAOImpl();
	
	@Test
	public void testCheckUser(){
		System.out.println(adminDAO.getCountByAdminNamePassword("admin", "amin"));
	}
	
	@Test
	public void testCheckAdminName(){
		System.out.println(adminDAO.getAdminNameCount("admin"));
	}
	
	@Test 
	public void testGet(){
		System.out.println(adminDAO.getAdminByName("admin"));
	}
}
