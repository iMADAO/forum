package com.project.service;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.project.bean.Admin;
import com.project.bean.User;
import com.project.dao.AdminDAO;
import com.project.dao.impl.AdminDAOImpl;
import com.project.exception.ErrorException;

public class AdminService {
	private AdminDAO adminDAO = new AdminDAOImpl();
	
	private boolean checkAdmin(String adminName, String password){
		Long count = adminDAO.getCountByAdminNamePassword(adminName, password);
		if(count<=0)
			return false;
		else
			return true;
	}
	
	public boolean checkIfAdminNameExists(String adminName){
		Long count = adminDAO.getAdminNameCount(adminName);
		if(count>0)
			return true;
		else 
			return false;
	}

	private Admin getAdminByName(String adminName) {
		return adminDAO.getAdminByName(adminName);
	}
	
	public Map<Admin, String> adminLogin(HttpServletRequest request, HttpServletResponse response, String adminName, String password){
		if(!checkAdmin(adminName, password)){
			System.out.println("用户名或密码错误");
			throw new ErrorException("用户名或密码错误");
		}
		
		System.out.println("登录成功");
		//普通用户被退出登录
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
		Admin admin = getAdminByName(adminName);
		admin.setPassword("");
		Map<Admin, String> map = new HashMap<Admin, String>();
		map.put(admin, tipMess);
		return map;
	}
}
