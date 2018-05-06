package com.project.handler;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.project.bean.MessageDTO;
import com.project.dao.MessageDAO;
import com.project.dao.impl.MessageDAOImpl;

public class PostServlet extends HttpServlet {
	private MessageDAO messageDTO = new MessageDAOImpl();
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		String postId = request.getParameter("postId");
		List<MessageDTO> messageDTOList = messageDTO.getMessageDTOByPostId(postId);
		System.out.println("size------" + messageDTOList.size());
		request.setAttribute("messageDTOList", messageDTOList);
		System.out.println(postId);
		request.getRequestDispatcher("/WEB-INF/view/post.jsp").forward(request, response);
	}
	
	
}
