package com.project.bean;

import java.util.Date;

public class Reply {
	private String replyId;
	private String userId;
	private String messId;
	private String replyContent;
	private int replyOrder;
	private Date createTime;
	private Date updateTime;
	private String username;
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getReplyId() {
		return replyId;
	}
	public void setReplyId(String replyId) {
		this.replyId = replyId;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getMessId() {
		return messId;
	}
	public void setMessId(String messId) {
		this.messId = messId;
	}
	public String getReplyContent() {
		return replyContent;
	}
	public void setReplyContent(String replyContent) {
		this.replyContent = replyContent;
	}
	public int getReplyOrder() {
		return replyOrder;
	}
	public void setReplyOrder(int replyOrder) {
		this.replyOrder = replyOrder;
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
		return "Reply [replyId=" + replyId + ", userId=" + userId + ", messId="
				+ messId + ", replyContent=" + replyContent + ", replyOrder="
				+ replyOrder + ", createTime=" + createTime + ", updateTime="
				+ updateTime + ", username=" + username + "]";
	}
	
	
	
}
