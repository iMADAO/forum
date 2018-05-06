package com.project.handler;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.project.bean.Admin;
import com.project.bean.Category;
import com.project.service.CategoryService;
import com.project.util.KeyUtil;

public class CategoryServlet extends HttpServlet{
	CategoryService categoryService = new CategoryService();
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String target = request.getParameter("target");
		if(target.equals("1")){
			request.getRequestDispatcher("/WEB-INF/view/addCategory.jsp").forward(request, response);
		}else if(target.equals("2")){
			HttpSession session = request.getSession();
			Admin admin = (Admin) session.getAttribute("admin");
			if(admin==null){
				session.setAttribute("tipMess", "管理员未登录");
				response.sendRedirect(request.getContextPath() + "/adminLogin");
				return;
			}
			deleteCategory(request, response);
		}else{
			request.getSession().setAttribute("tipMess", "该页面无法到达");
			response.sendRedirect(request.getContextPath() + "/admin");
		}
	}
	
	private void deleteCategory(HttpServletRequest request,
			HttpServletResponse response) {
		String categoryId = request.getParameter("categoryId");
		System.out.println("categoryId--------------------------"+categoryId);
		if(categoryId!=null && categoryId!=""){
			categoryService.deleteCategoryById(categoryId);
		}
		request.getSession().setAttribute("tipMess", "删除成功");
		try {
			response.sendRedirect(request.getContextPath() + "/admin");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
		
		String themeId = request.getParameter("themeId");
		String categoryName = request.getParameter("categoryName");
		Category category = new Category(KeyUtil.genUniquKey(), themeId, categoryName);
		categoryService.addCategory(category);
		session.setAttribute("tipMess", "添加成功");
		response.sendRedirect(request.getContextPath() + "/admin");
	}
}
