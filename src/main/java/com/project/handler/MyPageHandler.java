package com.project.handler;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.project.bean.Message;
import com.project.bean.Post;
import com.project.bean.PostDTO;
import com.project.bean.User;
import com.project.dao.MessageDAO;
import com.project.dao.PostDAO;
import com.project.dao.impl.MessageDAOImpl;
import com.project.dao.impl.PostDAOImpl;
import com.project.service.PostService;

public class MyPageHandler extends HttpServlet{
	PostService postService = new PostService();
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		User user = (User) request.getSession().getAttribute("user");
		if(user==null){
			request.getSession().setAttribute("tipMess", "用户未登录");
			response.sendRedirect(request.getContextPath() + "/toLogin");
			return;
		}
		List<PostDTO> postDTOList =  postService.getPostDTOByUserId(user.getUserId());
		System.out.println("myPage---" + request.getContextPath()+"/WEB-INF/view/myPage.jsp");
		request.setAttribute("postDTOList", postDTOList);
		request.getRequestDispatcher("/WEB-INF/view/myPage.jsp").forward(request, response);
	}
	
}
