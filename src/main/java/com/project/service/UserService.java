package com.project.service;

import java.util.List;

import com.project.bean.User;
import com.project.dao.UserDAO;
import com.project.dao.impl.UserDAOImpl;

public class UserService {

	private UserDAO userDAO = new UserDAOImpl();
	
	public User getUserById(String userId){
		return userDAO.getUserById(userId);
	}
	
	public User getUserByName(String username){
		return userDAO.getUserByUserName(username);
	}
	
	public List<User> get(){
		return userDAO.getAllUser();
	}
	
	public void addUser(User user){
		userDAO.addUser(user);
	}
	
	public boolean verifyPasswd(String name, String password){
		System.out.println(name + "-----------" + password);
		Long count = userDAO.getCountByNamePassword(name, password);
		System.out.println("count:------" + count);
		if(count<=0)
			return false;
		return true;
	}
	
	public void updateUserActiveState(String userId, byte activeState){
		userDAO.updateUserActiveState(userId, activeState);
	}
	
	public void update(String userId, byte validState){
		userDAO.updateUserValidState(userId, validState);
	}
	
	public boolean checkUserNameIfExist(String name){
		Long count = userDAO.getCountByName(name);
		if(count>0)
			return true;
		else 
			return false;
	}

	public void updatUser(User user) {
		userDAO.updateUser(user);
	}
}
