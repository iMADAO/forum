package com.project.bean;

public class Category {
	public Category() {
		super();
	}
	private String categoryId;
	private String themeId;
	private String categoryName;
	public String getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}
	public String getThemeId() {
		return themeId;
	}
	public void setThemeId(String themeId) {
		this.themeId = themeId;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	@Override
	public String toString() {
		return "Category [categoryId=" + categoryId + ", themeId=" + themeId
				+ ", categoryName=" + categoryName + "]";
	}
	public Category(String categoryId, String themeId, String categoryName) {
		super();
		this.categoryId = categoryId;
		this.themeId = themeId;
		this.categoryName = categoryName;
	}
	
	
	
}
