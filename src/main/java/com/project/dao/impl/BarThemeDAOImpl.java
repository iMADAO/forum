package com.project.dao.impl;

import java.util.List;

import com.project.bean.BarTheme;
import com.project.dao.BarThemeDAO;

public class BarThemeDAOImpl extends DAOImpl<BarTheme> implements BarThemeDAO {

	public void addTheme(BarTheme theme) {
		String sql = "insert into bar_theme(theme_id, theme_name) values(?, ?)";
		update(sql, theme.getId(), theme.getThemeName());
	}

	public List<BarTheme> getThemeList() {
		String sql = "select theme_id id, theme_name themeName from bar_theme";
		List<BarTheme> barThemeList = getForList(sql);
		return barThemeList;
	}

	public BarTheme getById(String id) {
		String sql = "select theme_id id, theme_name themeName from bar_theme where theme_id = ?";
		return get(sql, id);
	}

	public void deleteThemeByThemeId(String themeId) {
		String sql = "delete from bar_theme where theme_id=?";
		update(sql, themeId);
	}

}
