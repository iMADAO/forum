package com.project.bean;

import java.util.Date;
import java.util.List;

public class MessageDTO {
	private String messageId;
	private String postId;
	private String userId;
	private String username;
	private int postOrder;
	private String messContent;
	private Date createTime;
	private Date updateTime;
	List<String> picPathList;
	List<Reply> replyList;
	
	public List<String> getPicPathList() {
		return picPathList;
	}
	public void setPicPathList(List<String> picPathList) {
		this.picPathList = picPathList;
	}

	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	public List<Reply> getReplyList() {
		return replyList;
	}
	public void setReplyList(List<Reply> replyList) {
		this.replyList = replyList;
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
	public int getPostOrder() {
		return postOrder;
	}
	public void setPostOrder(int postOrder) {
		this.postOrder = postOrder;
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
	
	@Override
	public String toString() {
		return "MessageDTO [messageId=" + messageId + ", postId=" + postId
				+ ", userId=" + userId + ", username=" + username
				+ ", postOrder=" + postOrder + ", messContent=" + messContent
				+ ", createTime=" + createTime + ", updateTime=" + updateTime
				+ ", picPathList=" + picPathList + ", replyList=" + replyList
				+ "]";
	}
	
}
