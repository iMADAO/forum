package com.project.bean;

import java.util.Date;

public class Post {
	private String postId;
	private String themeId;
	private String categoryId;
	private Date createTime;
	private Date updateTime;
	private Byte isVisible = 0;
	private String userId;
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public Post() {
		super();
	}
	public Post(String postId, String themeId, String categoryId) {
		super();
		this.postId = postId;
		this.themeId = themeId;
		this.categoryId = categoryId;
	}
	public String getPostId() {
		return postId;
	}
	public void setPostId(String postId) {
		this.postId = postId;
	}
	public String getThemeId() {
		return themeId;
	}
	public void setThemeId(String themeId) {
		this.themeId = themeId;
	}
	public String getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	public Byte getIsVisible() {
		return isVisible;
	}
	public void setIsVisible(Byte isVisible) {
		this.isVisible = isVisible;
	}
	@Override
	public String toString() {
		return "Post [postId=" + postId + ", themeId=" + themeId
				+ ", categoryId=" + categoryId + ", createTime=" + createTime
				+ ", updateTime=" + updateTime + ", isVisible=" + isVisible
				+ ", userId=" + userId + "]";
	}
	
	
	
	
}
