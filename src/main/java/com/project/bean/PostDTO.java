package com.project.bean;

import java.util.Date;
import java.util.List;

public class PostDTO {
	private String postId;
	private String themeId;
	private String categoryId;
	private Date createTime;
	private Date updateTime;
	private Byte isVisible;
	private MessageDTO messageDTO;
	public Byte getIsVisible() {
		return isVisible;
	}

	public void setIsVisible(Byte isVisible) {
		this.isVisible = isVisible;
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

	@Override
	public String toString() {
		return "PostDTO [postId=" + postId + ", themeId=" + themeId
				+ ", categoryId=" + categoryId + ", createTime=" + createTime
				+ ", updateTime=" + updateTime + ", isVisible=" + isVisible
				+ ", messageDTO=" + messageDTO + "]";
	}

	public MessageDTO getMessageDTO() {
		return messageDTO;
	}

	public void setMessageDTO(MessageDTO messageDTO) {
		this.messageDTO = messageDTO;
	}

	public Byte getisVisible() {
		return isVisible;
	}

	public void setisVisible(Byte isVisible) {
		this.isVisible = isVisible;
	}


	
}
