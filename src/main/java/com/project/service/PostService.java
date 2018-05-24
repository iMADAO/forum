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
import com.project.util.PageUtils;
import com.project.web.Page;

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
			return postDTOList;
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
	
	public Page<PostDTO> getPostDTOListByCategoryInPage(String categoryId, Integer pageNo, Integer pageSize){
		//总记录数
		Long count = postDAO.getPostCountByCategoryId(categoryId);
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
	
		List<PostDTO> postDTOList = new ArrayList<PostDTO>();
		List<Post> postList = postDAO.getPostListByCategoryIdInPage(categoryId, startRecord, size);
		
		if(postList==null || postList.size()==0)
			return new Page<PostDTO>();
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
		PageUtils<PostDTO> pageUtils = new PageUtils<>();
		Page<PostDTO> page = pageUtils.getPage(postDTOList, count, pageSize, pageNo);
		return page;
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
