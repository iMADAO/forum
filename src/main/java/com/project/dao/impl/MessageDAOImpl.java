package com.project.dao.impl;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;

import com.project.bean.Message;
import com.project.bean.MessageDTO;
import com.project.bean.Reply;
import com.project.dao.MessPicDAO;
import com.project.dao.MessageDAO;
import com.project.dao.ReplyDAO;

public class MessageDAOImpl extends DAOImpl<Message> implements MessageDAO {
	private MessPicDAO messPicDAO = new MessPicDAOImpl();
	private ReplyDAO replyDAO = new ReplyDAOImpl();

	public Message getMessageById(String messageId) {
		String sql = "select message_id messageId, post_id postId, user_id userId, user_name username, post_order postOrder, mess_content messContent, create_time createTime, update_time updateTime from message where message_id = ?";
		return get(sql, messageId);
	}

	public List<Message> getMessageByPostId(String postId) {
		String sql = "select message_id messageId, post_id postId, user_id userId, user_name username, post_order postOrder, mess_content messContent, create_time createTime, update_time updateTime from message where post_id = ?";
		return getForList(sql, postId);
	}

	public void deleteMessById(String messageId) {
		String sql = "delete from message where message_id = ?";
		update(sql, messageId);

	}

	public void deleteMessByPostId(String postId) {
		String sql = "delete from message where post_id = ?";
		update(sql, postId);
	}

	public void addMessage(Message message) {
		String sql = "insert into message(message_id, post_id, user_id, user_name, post_order, mess_content) values(?, ?, ?, ?, ?, ?)";
		update(sql, message.getMessageId(), message.getPostId(), message.getuserId(), message.getUsername(), message.getPostOrder(), message.getMessContent());
	}

	public Message getMessageByPostIdInOrderOne(String postId) {
		String sql = "select message_id messageId, post_id postId, user_id userId, user_name username, post_order postOrder, mess_content messContent, create_time createTime, update_time updateTime from message where post_id = ? and post_order=1";
		return get(sql, postId);
	}

	public List<MessageDTO> getMessageDTOByPostId(String postId) {
		List<Message> messageList = getMessageByPostId(postId);
		List<MessageDTO> messageDTOList = new ArrayList<MessageDTO>();
		for(Message message: messageList){
			MessageDTO messageDTO = new MessageDTO();
			try {
				BeanUtils.copyProperties(messageDTO, message);
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			messageDTO.setPicPathList(messPicDAO.getMessPicPathList(message.getMessageId()));
			List<Reply> replyList = replyDAO.getReplyListByMessId(message.getMessageId());
			//对回复进行排序
			Comparator<Reply> c = (a, b)->{
				return a.getReplyOrder() - b.getReplyOrder();
			};
			Collections.sort(replyList, c);
			messageDTO.setReplyList(replyList);
			messageDTOList.add(messageDTO);
		}
		return messageDTOList;
	}
	
	public MessageDTO getMessageDTOByPostIdInOrderOne(String postId){
		Message message = getMessageByPostIdInOrderOne(postId);
		if(message==null){
			return null;
		}
		System.out.println("message-----------" + message);
		MessageDTO messageDTO = new MessageDTO();
		try {
			BeanUtils.copyProperties(messageDTO, message);
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(messageDTO);
		return messageDTO;
	}

	@Override
	public long getMessageCountByPostId(String postId) {
		String sql = "select count(*) from message where post_id = ?";
		return getForValue(sql, postId);
	}

	@Override
	public List<Message> getMessageByPostIdInPage(String postId,
			int startRecord, int size) {
		String sql = "select message_id messageId, post_id postId, user_id userId, user_name username, post_order postOrder, mess_content messContent, create_time createTime, update_time updateTime from message where post_id = ? limit ?, ?";
		return getForList(sql, postId, startRecord, size);
	}
}
