package com.project.bean;

import java.util.Date;

public class Message {
	public Message(String messageId, String postId, String userId,
			String username, int postOrder, String messContent) {
		super();
		this.messageId = messageId;
		this.postId = postId;
		this.userId = userId;
		this.username = username;
		this.postOrder = postOrder;
		this.messContent = messContent;
	}
	private String messageId;
	private String postId;
	private String userId;
	private String username;
	private int postOrder;
	private String messContent;
	private Date createTime;
	private Date updateTime;
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getuserId() {
		return userId;
	}
	public void setuserId(String userId) {
		this.userId = userId;
	}
	
	public String getMessageId() {
		return messageId;
	}
	public void setMessageId(String messageId) {
		this.messageId = messageId;
	}
	public String getPostId() {
		return postId;
	}
	public void setPostId(String postId) {
		this.postId = postId;
	}
	
	public String getMessContent() {
		return messContent;
	}
	public void setMessContent(String messContent) {
		this.messContent = messContent;
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
	
	public Message(String messageId, String postId, int postOrder,
			String messContent) {
		super();
		this.messageId = messageId;
		this.postId = postId;
		this.postOrder = postOrder;
		this.messContent = messContent;
	}

	public int getPostOrder() {
		return postOrder;
	}
	public void setPostOrder(int postOrder) {
		this.postOrder = postOrder;
	}
	public Message() {
		super();
	}
	@Override
	public String toString() {
		return "Message [messageId=" + messageId + ", postId=" + postId
				+ ", userId=" + userId + ", username=" + username
				+ ", postOrder=" + postOrder + ", messContent=" + messContent
				+ ", createTime=" + createTime + ", updateTime=" + updateTime
				+ "]";
	}
	
	
	
}
