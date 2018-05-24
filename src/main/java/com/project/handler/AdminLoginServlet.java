package com.project.handler;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.project.bean.Admin;
import com.project.bean.User;
import com.project.exception.ErrorException;
import com.project.service.AdminService;
import com.project.service.UserService;

public class AdminLoginServlet extends HttpServlet{
	private AdminService service = new AdminService();
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/view/adminLogin.jsp").forward(request, response);;
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String adminName = request.getParameter("adminName");
		String password = request.getParameter("password");
		String validateCodeForm = request.getParameter("validateCode");
		String validateCode = (String) request.getSession().getAttribute("CHECK_CODE_KEY");
		adminName = adminName.trim();
		password = password.trim();
		validateCodeForm = validateCodeForm.trim();
		
		if(adminName==null || adminName==""){
			request.setAttribute("ErrorMess", "用户名为空");
			request.getRequestDispatcher("/WEB-INF/view/adminLogin.jsp").forward(request, response);
			System.out.println("用户名为空");
			return;
		}
		if(password==null || password==""){
			request.setAttribute("adminName", adminName);
			request.setAttribute("ErrorMess", "密码为空");
			System.out.println("密码为空");
			request.getRequestDispatcher("/WEB-INF/view/adminLogin.jsp").forward(request, response);
			return;
		}
		
		if(!validateCode.equalsIgnoreCase(validateCodeForm)){
			request.setAttribute("adminName", adminName);
			System.out.println("验证码错误");
			request.setAttribute("ErrorMess", "验证码错误");
			request.getRequestDispatcher("/WEB-INF/view/adminLogin.jsp").forward(request, response);
			return;
		}
		Map<Admin, String> map = null;
		try {
			map = service.adminLogin(request, response, adminName, password);
		} catch (ErrorException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			request.setAttribute("ErrorMess", "用户名或密码错误");
			request.getRequestDispatcher("/WEB-INF/view/adminLogin.jsp").forward(request, response);
			return;
		}
		Admin admin = null;
		String tipMess = null;
		for(Map.Entry<Admin, String> entry: map.entrySet()){
			admin = entry.getKey();
			tipMess = entry.getValue();
		}
		request.getSession().setAttribute("admin", admin);
		request.getSession().setAttribute("tipMess", tipMess);
		response.sendRedirect(request.getContextPath() + "/admin");
	}

}
