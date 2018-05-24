package com.project.service;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;

import com.project.bean.Message;
import com.project.bean.MessageDTO;
import com.project.bean.Post;
import com.project.bean.PostDTO;
import com.project.bean.Reply;
import com.project.dao.MessPicDAO;
import com.project.dao.MessageDAO;
import com.project.dao.PostDAO;
import com.project.dao.ReplyDAO;
import com.project.dao.impl.MessPicDAOImpl;
import com.project.dao.impl.MessageDAOImpl;
import com.project.dao.impl.PostDAOImpl;
import com.project.dao.impl.ReplyDAOImpl;
import com.project.exception.ErrorException;
import com.project.util.KeyUtil;
import com.project.util.PageUtils;
import com.project.web.Page;

public class MessageService {
	private MessageDAO messageDAO = new MessageDAOImpl(); 
	private PostDAO postDAO = new PostDAOImpl();
	private MessPicDAO messPicDAO = new MessPicDAOImpl();
	private ReplyDAO replyDAO = new ReplyDAOImpl();
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
			throw new ErrorException("该贴不存在");
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

	public Page<MessageDTO> getMessageDTOPageByPostId(String postId, Integer pageNo, Integer pageSize) {
		long count = messageDAO.getMessageCountByPostId(postId);
		if(count==0L){
			return new Page<>();
		}
		
		//总页数
		int totalPage = (int) (count / pageSize);
		if(count % pageSize !=0){
			totalPage++;
		}
		
		System.out.println("count==" + count + "  totalPage=" + totalPage);
		if(totalPage<1){
			totalPage=1;
		}
		if(pageNo>totalPage){
			pageNo = totalPage;
		}
		if(pageNo<1)
			pageNo = 1;
		
		//起始记录数，在数据库从0开始算起
		int startRecord = (pageNo-1)*pageSize;
		//本页可以得到的记录数
		int size = (int) (startRecord +pageSize > count ? count - startRecord : pageSize);
		List<Message> messageList = messageDAO.getMessageByPostIdInPage(postId, startRecord, size);
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
		
		PageUtils<MessageDTO> pageUtils = new PageUtils<>();
		Page<MessageDTO> page = pageUtils.getPage(messageDTOList, count, pageSize, pageNo);
		return page;
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
