package com.project.handler;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
		System.out.println("logout-----------------------------------------");
		request.getSession().setAttribute("tipMess", "退出登录成功");
		response.sendRedirect((String) request.getSession().getAttribute("lastPage"));
	}
}
