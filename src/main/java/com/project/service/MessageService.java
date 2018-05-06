package com.project.service;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;

import Exception.ProjectException;

import com.project.bean.Message;
import com.project.bean.Post;
import com.project.bean.PostDTO;
import com.project.dao.MessageDAO;
import com.project.dao.PostDAO;
import com.project.dao.impl.MessageDAOImpl;
import com.project.dao.impl.PostDAOImpl;
import com.project.util.KeyUtil;

public class MessageService {
	private MessageDAO messageDAO = new MessageDAOImpl(); 
	private PostDAO postDAO = new PostDAOImpl();
	
	//发起帖子
	public String addFirstMessage(String userId, String username, String themeId, String categoryId, String messContent){
		String postId = KeyUtil.genUniquKey();
		Post post = new Post();
		post.setUserId(userId);
		post.setCategoryId(categoryId);
		post.setThemeId(themeId);
		post.setPostId(postId);
		postDAO.addPost(post);
		
		String messageId = KeyUtil.genUniquKey();
		Integer maxOrder = postDAO.getMaxOrderOfPost(postId);
		Message message = new Message(messageId, postId, maxOrder==null ? 1 : maxOrder+1, messContent);
		message.setUsername(username);
		message.setuserId(userId);
		System.out.println("message-=-=-=-=-====--=--=--=" + message);
		messageDAO.addMessage(message);
		return messageId;
	}
	
	//向帖子添加项
	public void addMessage(String themeId, String categoryId, String messContent, String postId){
		Post post = postDAO.getPostById(postId);
		if(post==null)
			throw new ProjectException("该贴不存在");
		Integer maxOrder = postDAO.getMaxOrderOfPost(postId);
		String messageId = KeyUtil.genUniquKey();
		Message message = new Message(messageId, postId, maxOrder+1, messContent);
		messageDAO.addMessage(message);
	}

	public String addMessage(String userId, String username, String theme, String postId, String messContent) {
		String messageId = KeyUtil.genUniquKey();
		Integer maxOrder = postDAO.getMaxOrderOfPost(postId);
		Integer postOrder = maxOrder==null ? 1 : maxOrder+1;
		Message message = new Message(messageId, postId, userId, username, postOrder, messContent);
		messageDAO.addMessage(message);
		return messageId;
	}
	
//	//根据帖子id 获取信息
//	public PostDTO getMessageListByPostId(String postId) throws IllegalAccessException, InvocationTargetException{
//		Post post = postDAO.getPostById(postId);
//		if(post==null)
//			throw new ProjectException("该贴不存在");
//		List<Message> messageList = messageDAO.getMessageByPostId(postId);
//		PostDTO postDTO = new PostDTO();
//		BeanUtils.copyProperties(post, postDTO);
//		postDTO.setMessageList(messageList);
//		return postDTO;
//		
//	}
}
