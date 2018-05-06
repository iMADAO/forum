package com.project.dao.impl;

import java.util.List;

import com.project.bean.User;
import com.project.dao.UserDAO;

public class UserDAOImpl extends DAOImpl<User> implements UserDAO{

	public User getUserById(String userId) {
		String sql = "select user_id userId, user_name userName, email, valid_state validState, active_state activeState from user where user_id = ?";
		User user = get(sql, userId);
		return user;
	}

	public void updateUserValidState(String userId, byte validState) {
		String sql = "update user set valid_state = ? where user_id = ?";
		update(sql, validState, userId);	
	}

	public void updateUserActiveState(String userId, byte activeState) {
		String sql = "update user set active_state = ? where user_id = ?";
		update(sql, activeState, userId);
		
	}

	public void addUser(User user) {
		String sql = "insert into user(user_id, user_name, password, email, valid_state, active_state) values(?, ?, ?, ?, ?, ?)";
		update(sql, user.getUserId(), user.getUserName(), user.getPassword(), user.getEmail(), user.getValidState(), user.getActiveState());
	}

	public List<User> getAllUser() {
		String sql = "select user_id userId, user_name userName, email, valid_state validState, active_state activeState from user";
		return getForList(sql);
	}

	public Long getCountByNamePassword(String name, String password){
		String sql = "select count(*) from user where user_name = ? and password = ?";
		Long count = getForValue(sql, name, password);
		return count;
	}
	
	public Long getCountByName(String name){
		String sql = "select count(*) from user where user_name=?";
		Long count = getForValue(sql, name);
		return count;
	}

	public User getUserByUserName(String username) {
		String sql = "select user_id userId, user_name userName, email, valid_state validState, active_state activeState from user where user_name = ?";
		return get(sql, username);
	}

	public void updateUser(User user) {
		String sql = "update user set user_name=?, email=? where user_id = ?";
		update(sql, user.getUserName(), user.getEmail(), user.getUserId());
	}

}
