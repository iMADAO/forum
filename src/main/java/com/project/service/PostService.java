package com.project.service;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;

import com.project.bean.MessageDTO;
import com.project.bean.Post;
import com.project.bean.PostDTO;
import com.project.dao.MessPicDAO;
import com.project.dao.MessageDAO;
import com.project.dao.PostDAO;
import com.project.dao.impl.MessPicDAOImpl;
import com.project.dao.impl.MessageDAOImpl;
import com.project.dao.impl.PostDAOImpl;

public class PostService {
	private PostDAO postDAO = new PostDAOImpl();
	private MessageDAO messageDAO = new MessageDAOImpl();
	private MessPicDAO messPicDAO = new MessPicDAOImpl();
	
	public void addPost(Post post){
		postDAO.addPost(post);
	}
	
	public List<Post> getPostListByCategoryId(String categoryId){
		return postDAO.getPostListByCategoryId(categoryId);
	}
	
	public List<PostDTO> getPostDTOListByCategory(String categoryId){
		List<PostDTO> postDTOList = new ArrayList<PostDTO>();
		List<Post> postList = postDAO.getPostListByCategoryId(categoryId);
		if(postList==null || postList.size()==0)
			return null;
		for(Post post: postList){
			PostDTO postDTO = new PostDTO();
			try {
				BeanUtils.copyProperties(postDTO, post);
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("post==========" + post);
			MessageDTO messageDTO = messageDAO.getMessageDTOByPostIdInOrderOne(post.getPostId());
			System.out.println("messageDTO=========" + messageDTO);
			postDTO.setMessageDTO(messageDTO);
			if(messageDTO!=null){
				List<String> picPathList = messPicDAO.getMessPicPathList(postDTO.getMessageDTO().getMessageId());
				postDTO.getMessageDTO().setPicPathList(picPathList);
			}
			postDTOList.add(postDTO);
		}
		return postDTOList;
	}

	public Integer getMaxMessageOrder(String postId) {
		return postDAO.getMaxOrderOfPost(postId);
	}

	public List<PostDTO> getPostDTOByUserId(String userId) {
		List<Post> postList = postDAO.getPostListByUserId(userId);
		List<PostDTO> postDTOList = new ArrayList<PostDTO>();
		for(Post post: postList){
			PostDTO postDTO = new PostDTO();
			try {
				BeanUtils.copyProperties(postDTO, post);
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			MessageDTO messageDTO = messageDAO.getMessageDTOByPostIdInOrderOne(post.getPostId());
			if(messageDTO!=null){
				messageDTO.setPicPathList(messPicDAO.getMessPicPathList(messageDTO.getMessageId()));
				postDTO.setMessageDTO(messageDTO);
			}
		
			postDTOList.add(postDTO);
		}
		return postDTOList;
	}

}
