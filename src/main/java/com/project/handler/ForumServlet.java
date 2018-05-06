package com.project.handler;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.project.bean.BarThemeDTO;
import com.project.service.ThemeService;

public class ForumServlet extends HttpServlet {
	private ThemeService themeService = new ThemeService();
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		List<BarThemeDTO> themeDTOList =  themeService.getBarThemeDTOList();
		System.out.println(themeDTOList);
		request.setAttribute("themeDTOList", themeDTOList);
		request.getRequestDispatcher("/WEB-INF/view/forum.jsp").forward(request, response);;
	}
}
