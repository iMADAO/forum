package com.project.dao;

import java.util.List;

import com.project.bean.Post;

public interface PostDAO {
	Post getPostById(String postId);
	List<Post> getPostListByCategoryId(String categoryId);
	void addPost(Post post);
	void deletePost(String postId);
	Integer getMaxOrderOfPost(String postId);
	void setPostVisualState(String postId, Byte state);
	List<Post> getPostListByUserId(String userId);
	Long getPostCountByCategoryId(String categoryId);
	List<Post> getPostListByCategoryIdInPage(String categoryId,
			int startRecord, int size);
}
