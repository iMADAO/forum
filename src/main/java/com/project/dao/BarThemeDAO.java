package com.project.dao;

import java.util.List;

import com.project.bean.BarTheme;

public interface BarThemeDAO {
	void addTheme(BarTheme theme);
	List<BarTheme> getThemeList();
	BarTheme getById(String id);
	void deleteThemeByThemeId(String themeId);
}
