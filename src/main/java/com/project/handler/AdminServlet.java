package com.project.handler;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.project.bean.BarThemeDTO;
import com.project.service.CategoryService;
import com.project.service.ThemeService;

public class AdminServlet extends HttpServlet{
	private CategoryService categoryService = new CategoryService();
	private ThemeService themeService = new ThemeService();
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<BarThemeDTO> themeDTOList = themeService.getBarThemeDTOList();
		request.setAttribute("themeDTOList", themeDTOList);
		request.getRequestDispatcher("/WEB-INF/view/admin.jsp").forward(request, response);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

}
