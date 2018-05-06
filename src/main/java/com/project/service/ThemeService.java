package com.project.service;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;

import com.project.bean.BarTheme;
import com.project.bean.BarThemeDTO;
import com.project.bean.Category;
import com.project.dao.BarThemeDAO;
import com.project.dao.CategoryDAO;
import com.project.dao.impl.BarThemeDAOImpl;
import com.project.dao.impl.CategoryDAOImpl;

public class ThemeService {

	private BarThemeDAO themeDAO = new BarThemeDAOImpl();
	private CategoryDAO categoryDAO = new CategoryDAOImpl();
	
	public BarThemeDTO getBarThemeDTOById(String themeId) throws IllegalAccessException, InvocationTargetException{
		BarTheme theme = themeDAO.getById(themeId); 
		List<Category> categoryList = categoryDAO.getCategoryByThemeId(themeId);
		BarThemeDTO barThemeDTO = new BarThemeDTO();
		BeanUtils.copyProperties(theme, barThemeDTO);
		barThemeDTO.setCategoryList(categoryList);
		return barThemeDTO;
	}
	
	public List<BarThemeDTO> getBarThemeDTOList(){
		List<BarTheme> themeList = themeDAO.getThemeList();
		List<BarThemeDTO> themeDTOList = new ArrayList<BarThemeDTO>();
		for(BarTheme theme: themeList){
			BarThemeDTO themeDTO = new BarThemeDTO();
			try {
				BeanUtils.copyProperties(themeDTO, theme);
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			themeDTO.setCategoryList(categoryDAO.getCategoryByThemeId(theme.getId()));
			themeDTOList.add(themeDTO);
		}
		return themeDTOList;
	}
	public void addTheme(BarTheme theme){
		themeDAO.addTheme(theme);
	}

	public void deleteThemeByThemeId(String themeId) {
		themeDAO.deleteThemeByThemeId(themeId);
		categoryDAO.deleteCategoryByThemeId(themeId);
	}
	
	
	
}
