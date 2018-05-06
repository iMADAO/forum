package com.project.bean;

public class User {
	public User(String userId, String userName, String password, String email) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.password = password;
		this.email = email;

	}
	
	public User(){}
	private String userId;
	private String userName;
	private String password;
	private String email;
	private byte validState;
	private byte activeState;
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public byte getValidState() {
		return validState;
	}
	public void setValidState(byte validState) {
		this.validState = validState;
	}
	public byte getActiveState() {
		return activeState;
	}
	public void setActiveState(byte activeState) {
		this.activeState = activeState;
	}
	@Override
	public String toString() {
		return "User [userId=" + userId + ", userName=" + userName + ", password=" + password + ", email=" + email
				+ ", validState=" + validState + ", activeState=" + activeState + "]";
	}
	
	
	
}