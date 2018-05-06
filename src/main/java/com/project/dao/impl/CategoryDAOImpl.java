package com.project.dao.impl;

import java.util.List;

import com.project.bean.Category;
import com.project.dao.CategoryDAO;

public class CategoryDAOImpl extends DAOImpl<Category> implements CategoryDAO {

	public List<Category> getCategoryByThemeId(String themeId) {
		String sql = "select category_id categoryId, theme_id themeId, category_name categoryName from category where theme_id = ?";
		return getForList(sql, themeId);
	}

	public Category getCategoryById(String categoryId) {
		String sql = "select category_id categoryId, theme_id themeId, category_name categoryName from category where category_id = ?";
		return get(sql, categoryId);
	}

	public void deleteCategory(String categoryId) {
		String sql = "delete from category where category_id = ?";
		update(sql, categoryId);
	}
	
	public void addCategory(Category category){
		String sql = "insert into category (category_id, theme_id, category_name) values(?, ?, ?)";
		update(sql, category.getCategoryId(), category.getThemeId(), category.getCategoryName());
	}

	public void deleteCategoryByThemeId(String themeId) {
		String sql = "delete from category where theme_id = ?";
		update(sql, themeId);
	}

}
