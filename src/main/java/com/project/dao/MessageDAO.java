package com.project.dao;

import java.util.List;

import com.project.bean.Message;
import com.project.bean.MessageDTO;

public interface MessageDAO {
	Message getMessageById(String messId);
	List<Message> getMessageByPostId(String postId);
	void deleteMessById(String messId);
	void deleteMessByPostId(String postId);
	void addMessage(Message message);
	Message getMessageByPostIdInOrderOne(String postId);
	List<MessageDTO> getMessageDTOByPostId(String postId);
	MessageDTO getMessageDTOByPostIdInOrderOne(String postId);
	long getMessageCountByPostId(String postId);
	List<Message> getMessageByPostIdInPage(String postId, int startRecord,
			int size);
}
