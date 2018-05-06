import java.awt.image.BufferedImage;
import java.sql.Connection;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import com.project.bean.User;
import com.project.dao.UserDAO;
import com.project.dao.impl.UserDAOImpl;
import com.project.util.JdbcUtils;
import com.project.util.VerificationCodeUtil;

public class MyTest {
	UserDAO userDAO = new UserDAOImpl();

	@Test
	public void testConnection(){
		Connection connection = null;
		try {
			connection = JdbcUtils.getConnection();
			System.out.println(connection);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			JdbcUtils.releaseDB(null, null,  connection);
		}
	}
	
	@Test
	public void test2(){
		User user = userDAO.getUserById("1003");
		System.out.println(user);
		
	}
	
	@Test
	public void test3(){
		List<User> list = userDAO.getAllUser();
		for(User user: list){
			System.out.println(user);
		}
		System.out.println(list.size());
	}
	
	@Test
	public void testgetUserCount(){
		System.out.println(userDAO.getCountByNamePassword("tom", "abc"));
	}
	
	@Test
	public void testAddUser(){
		User user = new User("1003", "Nike", "123", "123@163.com");
		userDAO.addUser(user);
	}
	
	@Test
	public void testUpdateUserActiveState(){
		userDAO.updateUserActiveState("1001", (byte)2);
	}
	
	@Test 
	public void testupdateUserValidState(){
		userDAO.updateUserValidState("1001", (byte)2);
	}
	
	@Test
	public void testValidate(){
		Map<String, BufferedImage> map = VerificationCodeUtil.getVerificationCode();
	}
	
	public static void main(String args[]){
		Connection connection = null;
		try {
			connection = JdbcUtils.getConnection();
			System.out.println(connection);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			JdbcUtils.releaseDB(null, null,  connection);
		}
	}
	
	@Test
	public void testGetByName(){
		System.out.println(userDAO.getCountByName("tom"));
	}
}
