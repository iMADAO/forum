package com.project.handler;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.project.bean.Message;
import com.project.bean.MessageDTO;
import com.project.dao.MessageDAO;
import com.project.dao.impl.MessageDAOImpl;
import com.project.service.MessageService;
import com.project.web.Page;

public class PostServlet extends HttpServlet {
	MessageService messageService = new MessageService();
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		String postId = request.getParameter("postId");
		Integer pageNo = 0;
		Integer pageSize = 0;
		try {
			pageNo = Integer.parseInt(request.getParameter("pageNo"));
			pageSize = Integer.parseInt(request.getParameter("pageSize"));
		} catch (Exception e) {
			pageNo = 1;
			pageSize = 10;
			e.printStackTrace();
		}
		Page<MessageDTO> page = messageService.getMessageDTOPageByPostId(postId, pageNo, pageSize);
		request.setAttribute("page", page);
		System.out.println(postId);
		request.getRequestDispatcher("/WEB-INF/view/post.jsp").forward(request, response);
	}
	
	
}
