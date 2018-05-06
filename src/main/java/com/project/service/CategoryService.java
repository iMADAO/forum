package com.project.service;

import java.util.List;

import com.project.bean.Category;
import com.project.dao.CategoryDAO;
import com.project.dao.PostDAO;
import com.project.dao.impl.CategoryDAOImpl;
import com.project.dao.impl.PostDAOImpl;

public class CategoryService {
	private CategoryDAO categoryDAO = new CategoryDAOImpl();
	private PostDAO postDAO = new PostDAOImpl();
	private PostService postService = new PostService();
	
	public List<Category> getCategoryListByThemeId(String themeId){
		return categoryDAO.getCategoryByThemeId(themeId);
	}
	
	public void addCategory(Category category){
		categoryDAO.addCategory(category);
	}

	public Category getCategoryById(String categoryId) {
		return categoryDAO.getCategoryById(categoryId);
	}
	
	public void deleteCategoryByThemeId(String themeId){
		categoryDAO.deleteCategoryByThemeId(themeId);
	}

	public void deleteCategoryById(String categoryId) {
		categoryDAO.deleteCategory(categoryId);
		
	}
}
