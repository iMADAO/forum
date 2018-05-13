package com.project.handler;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LogoutServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getSession().removeAttribute("user");
		Cookie cookieName = new Cookie("username", null);
		cookieName.setMaxAge(0);
		Cookie cookiePassword = new Cookie("password", null);
		cookiePassword.setMaxAge(0);
		response.addCookie(cookieName);
		response.addCookie(cookiePassword);
		HttpSession session = request.getSession();
		System.out.println("logout-----------------------------------------");
		session.setAttribute("tipMess", "退出登录成功");
		String lastPage = (String) session.getAttribute("lastPage");
		if(lastPage!=null && lastPage!=""){
			response.sendRedirect(lastPage);
		}else{
			response.sendRedirect(request.getContextPath() + "/index");
		}
	}
}
