package com.project.bean;

public class BarTheme {
	private String id;
	private String themeName;
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
	@Override
	public String toString() {
		return "BarTheme [id=" + id + ", themeName=" + themeName + "]";
	}
}
