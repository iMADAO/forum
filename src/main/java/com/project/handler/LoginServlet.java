package com.project.handler;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.project.bean.User;
import com.project.service.UserService;

public class LoginServlet extends HttpServlet {
	
	private UserService service = new UserService();
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
//		Cookie[] cookies = request.getCookies();
//		String username = null;
//		String password =  null;
//		HttpSession session = request.getSession();
//		for(Cookie cookie: cookies){
//			if(cookie.getName().equals("username"))
//				username = cookie.getValue();
//			else if(cookie.getName().equals("password")){
//				password = cookie.getValue();
//			}
//		}
//		
//		System.out.println("username----" + username + "password----" + password);
//		if(username!=null && username!="" && password!=null && password!=""){
//			if(service.verifyPasswd(username, password)){
//				User user = service.getUserByName(username);
//				user.setPassword("");
//				session.setAttribute("user", user);
//				String lastPage = (String) session.getAttribute("lastPage");
//				if(lastPage==null || lastPage=="")
//					lastPage = request.getContextPath() + "/index.jsp";
//				response.sendRedirect(lastPage);
//				return;
//			}
//		}
		System.out.println(request.getContextPath() + "/WEB-INF/view/login.jsp");
		request.getRequestDispatcher("/WEB-INF/view/login.jsp").forward(request, response);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String validateCodeForm = request.getParameter("validateCode");
		String validateCode = (String) request.getSession().getAttribute("CHECK_CODE_KEY");
		String  save = request.getParameter("save");
		String lastPage = (String) request.getSession().getAttribute("lastPage");
		System.out.println(lastPage);
		System.out.println(save);
		System.out.println("-------------");
		Cookie[] cookies = request.getCookies();
		for (int i = 0; i < cookies.length; i++) {
			System.out.println(cookies[i].getName() + "======" + cookies[i].getValue());
		}
		
	
//		Cookie cookie = request.getSession().getC;
		
		if(username==null || username==""){
			request.setAttribute("ErrorMess", "用户名为空");
			request.getRequestDispatcher("/WEB-INF/view/login.jsp").forward(request, response);
			System.out.println("用户名为空");
			return;
		}
		if(password==null || password==""){
			request.setAttribute("username", username);
			request.setAttribute("ErrorMess", "密码为空");
			System.out.println("密码为空");
			request.getRequestDispatcher("/WEB-INF/view/login.jsp").forward(request, response);
			return;
		}
		
		if(!validateCode.equalsIgnoreCase(validateCodeForm)){
			request.setAttribute("username", username);
			System.out.println("验证码错误");
			request.setAttribute("ErrorMess", "验证码错误");
			request.setAttribute("username", username);
			request.getRequestDispatcher("/WEB-INF/view/login.jsp").forward(request, response);
			return;
		}
		
		System.out.println(username);
		System.out.println(password);
		if(!service.verifyPasswd(username, password)){
			System.out.println("用户名或密码错误");
			request.setAttribute("username", username);
			request.setAttribute("ErrorMess", "用户名或密码错误");
			request.setAttribute("username", username);
			request.getRequestDispatcher("/WEB-INF/view/login.jsp").forward(request, response);
			return;
		}
		
		System.out.println("登录成功");
		User user = service.getUserByName(username);
		user.setPassword("");
		HttpSession session = request.getSession();
		session.setAttribute("user", user);
		
		if(save!=null && save.equals("on")){
			Cookie cookieName = new Cookie("username", username);
			Cookie cookiePassword = new Cookie("password", password);
			cookieName.setMaxAge(60*60*24*7);
			cookiePassword.setMaxAge(60*60*24*7);
			response.addCookie(cookieName);
			response.addCookie(cookiePassword);
			System.out.println("保存cookie---------------------");
		}
		
		String tipMess="登录成功";
		if(session.getAttribute("admin")!=null){
			tipMess+="-管理员已退出登录";
			session.removeAttribute("admin");
		}
		session.removeAttribute("admin");
		session.setAttribute("tipMess", tipMess);
		if(lastPage!=null && lastPage!=""){
			response.sendRedirect(lastPage);
		}else{
			System.out.println(request.getContextPath() + "/WEB-INF/view/index.jsp");
			response.sendRedirect(request.getContextPath() + "/index");
		}
	}
		
}

