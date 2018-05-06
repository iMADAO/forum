package com.project.bean;

import java.util.List;

public class BarThemeDTO {
	private String id;
	private String themeName;
	private List<Category> categoryList;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getThemeName() {
		return themeName;
	}
	public void setThemeName(String themeName) {
		this.themeName = themeName;
	}
	public List<Category> getCategoryList() {
		return categoryList;
	}
	public void setCategoryList(List<Category> categoryList) {
		this.categoryList = categoryList;
	}
	@Override
	public String toString() {
		return "BarThemeDTO [id=" + id + ", themeName=" + themeName
				+ ", categoryList=" + categoryList + "]";
	}
	
}
