package com.project.handler;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.project.bean.Admin;
import com.project.bean.BarTheme;
import com.project.service.ThemeService;
import com.project.util.KeyUtil;

public class ThemeServlet extends HttpServlet {
	private ThemeService themeService = new ThemeService();
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String target= request.getParameter("target");
		if(target.equals("1")){
			request.getRequestDispatcher("/WEB-INF/view/addTheme.jsp").forward(request, response);
		}else if(target.equals("2")){
			HttpSession session = request.getSession();
			Admin admin = (Admin) session.getAttribute("admin");
			if(admin==null){
				session.setAttribute("tipMess", "管理员未登录");
				response.sendRedirect(request.getContextPath() + "/adminLogin");
				return;
			}
			deleteTheme(request, response);
		}else{
			request.getSession().setAttribute("tipMess", "该页面无法到达");
			response.sendRedirect(request.getContextPath() + "/admin");
		}
		
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		Admin admin = (Admin) session.getAttribute("admin");
		if(admin==null){
			session.setAttribute("tipMess", "管理员未登录");
			response.sendRedirect(request.getContextPath() + "/adminLogin");
			return;
		}
		String target = request.getParameter("target");
		System.out.println("target--------------------" + target);
		addTheme(request, response);
	}
	
	private void addTheme(HttpServletRequest request, HttpServletResponse response){
		String themeName = request.getParameter("themeName");
		if(themeName==null || themeName==""){
			request.setAttribute("ErrorMess", "主题名为空");
			try {
				request.getRequestDispatcher("/WEB-INF/view/addTheme.jsp").forward(request, response);
			} catch (ServletException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return;
		}
		System.out.println(themeName);
		BarTheme barTheme = new BarTheme();
		barTheme.setId(KeyUtil.genUniquKey());
		barTheme.setThemeName(themeName);
		themeService.addTheme(barTheme);
		request.getSession().setAttribute("tipMess", "添加主题成功");
		try {
			response.sendRedirect(request.getContextPath() + "/admin");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	private void  deleteTheme(HttpServletRequest request, HttpServletResponse response){
		String themeId = request.getParameter("themeId");
		System.out.println(themeId);
		if(themeId!=null && themeId!=""){
			themeService.deleteThemeByThemeId(themeId);
		}
		request.getSession().setAttribute("tipMess", "删除主题成功");
		try {
			response.sendRedirect(request.getContextPath() + "/admin");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
