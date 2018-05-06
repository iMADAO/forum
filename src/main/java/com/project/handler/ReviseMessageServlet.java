package com.project.handler;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.project.bean.User;
import com.project.service.UserService;


public class ReviseMessageServlet extends HttpServlet{
	private UserService userService = new UserService();
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		User user = (User) request.getSession().getAttribute("user");
		if(user==null){
			request.getSession().setAttribute("tipMess", "用户未登录");
			response.sendRedirect(request.getContextPath() + "/toLogin");
			return;
		}
		
		String target = request.getParameter("target");
		System.out.println("target-----------------------------" + target);
		request.setAttribute("target", target);
		request.getRequestDispatcher("/WEB-INF/view/reviseMessage.jsp").forward(request, response);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String target = request.getParameter("target");
		System.out.println(target);
		String username = request.getParameter("username");
		String email = request.getParameter("email");
		
		System.out.println(username + "--------=-=--------------" + email);
		User user = (User) request.getSession().getAttribute("user");
		if(user==null){
			request.getSession().setAttribute("tipMess", "用户未登录");
			response.sendRedirect(request.getContextPath() + "/toLogin");
			return;
		}
		
		if(target.equals("username")){
			if(username==null || username.equals("")){
				request.getSession().setAttribute("tipMess", "用户名不能为空");
				response.sendRedirect(request.getContextPath() + "/reviseMessage?target=" + target);
				return;
			}
		}else if(target.equals("email")){
			if(email==null || email.equals("")){
				request.getSession().setAttribute("tipMess", "邮箱不能为空");
				response.sendRedirect(request.getContextPath() + "/reviseMessage?target=" + target);
				return;
			}
			
			String regex = "\\w+@\\w+(\\.\\w{2,3})*\\.\\w{2,3}";
			if(!email.matches(regex)){
				request.getSession().setAttribute("tipMess", "邮箱格式不正确");
				response.sendRedirect(request.getContextPath() + "/reviseMessage?target=" + target);
				return;
			}
		}
		
		if(target.equals("username")){
			username = username.trim();
			if(username!=user.getUserName()){
				if(userService.checkUserNameIfExist(username)){
					request.getSession().setAttribute("tipMess", "该用户名已存在");
					response.sendRedirect(request.getContextPath() + "/reviseMessage?target=" + target);
					return;
				}
			}
			user.setUserName(username);
		}else if(target.equals("email")){
			email = email.trim();
			user.setEmail(email);
		}else{
			
		}
		System.out.println(user);
		userService.updatUser(user);
		request.getSession().setAttribute("tipMess", "修改用户信息成功");
		response.sendRedirect(request.getContextPath() + "/myPage");
	}

}
