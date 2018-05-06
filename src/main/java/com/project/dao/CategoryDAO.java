package com.project.dao;

import java.util.List;

import com.project.bean.Category;

public interface CategoryDAO {
	List<Category> getCategoryByThemeId(String themeId);
	Category getCategoryById(String categoryId);
	void deleteCategory(String categoryId);
	void addCategory(Category category);
	void deleteCategoryByThemeId(String themeId);
}
