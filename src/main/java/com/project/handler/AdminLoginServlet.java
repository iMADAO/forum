package com.project.handler;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.project.bean.Admin;
import com.project.bean.User;
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
		
		if(!service.checkAdmin(adminName, password)){
			System.out.println("用户名或密码错误");
			request.setAttribute("adminName", adminName);
			request.setAttribute("ErrorMess", "用户名或密码错误");
			request.getRequestDispatcher("/WEB-INF/view/adminLogin.jsp").forward(request, response);
			return;
		}
		
		System.out.println("登录成功");
		//普通用户退出登录
		String tipMess = "登录成功";
		User user = (User) request.getSession().getAttribute("user");
		if(user!=null){
			tipMess+=",用户" + user.getUserName() + "已退出登录";
			request.getSession().removeAttribute("user");
			
			Cookie cookieName = new Cookie("username", null);
			cookieName.setMaxAge(0);
			Cookie cookiePassword = new Cookie("password", null);
			cookiePassword.setMaxAge(0);
			response.addCookie(cookieName);
			response.addCookie(cookiePassword);
		}
		Admin admin = service.getAdminByName(adminName);
		admin.setPassword("");
		request.getSession().setAttribute("admin", admin);
		request.getSession().setAttribute("tipMess", tipMess);
		response.sendRedirect(request.getContextPath() + "/admin");
	}

}
