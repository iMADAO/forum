package com.project.dao.impl;

import java.util.List;

import com.project.bean.Post;
import com.project.dao.PostDAO;

public class PostDAOImpl extends DAOImpl<Post> implements PostDAO{

	public Post getPostById(String postId) {
		String sql = "select post_id postId, theme_id themeId, category_id categoryId, user_id userId, create_time createTime,  update_time updateTime, is_visible isVisible from post where post_id = ?";
		Post post = get(sql, postId);
		return post;
	}

	public List<Post> getPostListByCategoryId(String categoryId) {
		String sql = "select post_id postId, theme_id themeId, category_id categoryId, user_id userId, create_time createTime, update_time updateTime, is_visible isVisible from post where category_id = ?";
		return getForList(sql, categoryId);
	}

	public void addPost(Post post) {
		String sql = "insert into post(post_id, theme_id, category_id, user_id) values(?, ?, ?, ?)";
		update(sql, post.getPostId(), post.getThemeId(), post.getCategoryId(), post.getUserId());
	}

	public void deletePost(String postId) {
		String sql = "delete from post where post_id = ?";
		update(sql, postId);
	}

	public Integer getMaxOrderOfPost(String postId) {
		String sql = "select max(post_order) from message where post_id = ?";
		return getForValue(sql, postId);
	}

	public void setPostVisualState(String postId, Byte state) {
		String sql = "update post set is_visible = ? where post_id = ?";
		update(sql, state, postId);
	}

	public List<Post> getPostListByUserId(String userId) {
		String sql = "select post_id postId, theme_id themeId, category_id categoryId, user_id userId, create_time createTime,  update_time updateTime, is_visible isVisible from post where user_id = ?";
		return getForList(sql, userId);
	}

	
	
	
}
