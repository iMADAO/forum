package com.project.dao;

import java.util.List;

import com.project.bean.User;

public interface UserDAO {
	User getUserById(String userId);
	void updateUserValidState(String userId, byte validState);
	void updateUserActiveState(String userId, byte activeState);
	void addUser(User user);
	List<User> getAllUser();
	Long getCountByNamePassword(String name, String password);
	Long getCountByName(String name);
	User getUserByUserName(String username);
	void updateUser(User user);
}
