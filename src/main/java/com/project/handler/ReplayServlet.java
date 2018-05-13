package com.project.handler;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.project.bean.Reply;
import com.project.bean.User;
import com.project.dao.ReplyDAO;
import com.project.dao.impl.ReplyDAOImpl;
import com.project.util.KeyUtil;

public class ReplayServlet extends HttpServlet{
	ReplyDAO replyDAO = new ReplyDAOImpl();
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		request.getRequestDispatcher("/WEB-INF/view/reply.jsp").forward(request, response);;
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response){
		String replyContent = (String) request.getParameter("replyContent");
		String messId = (String) request.getParameter("messageId");
		System.out.println("messId-----" + messId);
		User user = (User) request.getSession().getAttribute("user");
		if(user==null){
			request.getSession().setAttribute("tipMess", "用户未登录");
			try {
				request.getRequestDispatcher("/WEB-INF/view/reply.jsp").forward(request, response);
			} catch (ServletException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return;
		}
		Integer maxOrder = replyDAO.getMaxOrderByMessageId(messId);
		Reply reply = new Reply();
		reply.setUsername(user.getUserName());
		reply.setMessId(messId);
		reply.setReplyContent(replyContent);
		reply.setReplyId(KeyUtil.genUniquKey());
		reply.setUserId(user.getUserId());
		reply.setReplyOrder(maxOrder==null ? 1 : maxOrder+1);
		System.out.println("Reply-------" + reply);
		replyDAO.addReply(reply);
		try {
			request.getSession().setAttribute("tipMess", "添加回复成功");
			response.sendRedirect(request.getContextPath() + "/post?postId=" + request.getParameter("postId"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
