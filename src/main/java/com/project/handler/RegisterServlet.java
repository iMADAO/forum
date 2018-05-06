package com.project.handler;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.project.bean.User;
import com.project.service.UserService;
import com.project.util.KeyUtil;

public class RegisterServlet extends HttpServlet {
	private UserService userService = new UserService();
	private String CHECK_CODE_KEY = "CHECK_CODE_KEY";
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		request.getRequestDispatcher("/WEB-INF/view/register.jsp").forward(request, response);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String repeatPassword = request.getParameter("repeatPassword");
		String email = request.getParameter("email");
		String verificationCodeForm = (String) request.getParameter("verificationCode");
		String verificationCode = (String) request.getSession().getAttribute(CHECK_CODE_KEY);
		System.out.println(verificationCode + "-----------" + verificationCodeForm);
		
		String regex = "\\w+@\\w+(\\.\\w{2,3})*\\.\\w{2,3}";
		
		boolean emailFlag = true;
		if(!email.matches(regex)){
			emailFlag = false;
		}
		
		boolean flag = true;
		if(username==null|| username==""){
			request.setAttribute("ErrorMess", "用户名为空");
			flag = false;
		}else if(password==null || password=="" || repeatPassword==null || repeatPassword ==""){
			request.setAttribute("ErrorMess", "密码为空");
			flag = false;
		}else if(email==null || email==""){
			request.setAttribute("ErrorMess", "邮箱为空");
			flag = false;
		}else if(!emailFlag){
			request.setAttribute("ErrorMess", "邮箱格式不正确");
			flag = false;
		}else if(verificationCodeForm==null || verificationCodeForm==""){
			request.setAttribute("ErrorMess", "验证码为空");
			flag = false;
		}else if(!password.equals(repeatPassword)){
			request.setAttribute("ErrorMess", "前后密码不匹配");
			flag = false;
		}else if(!verificationCode.equalsIgnoreCase(verificationCodeForm)){
			request.setAttribute("ErrorMess", "验证码错误");
			flag = false;
		}else if(userService.checkUserNameIfExist(username)){
			request.setAttribute("ErrorMess", "用户名已存在");
			flag = false;
		}
		
		if(!flag){
			request.getRequestDispatcher("/WEB-INF/view/register.jsp").forward(request, response);
			return;
		}
		
		String userId = KeyUtil.genUniquKey();
		User user = new User(userId, username, password, email);
		userService.addUser(user);
		request.getSession().setAttribute("tipMess", "注册成功");
		response.sendRedirect(request.getContextPath() + "/toLogin");
	}

}
