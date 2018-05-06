package com.project.handler;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.project.bean.Category;
import com.project.bean.Post;
import com.project.bean.PostDTO;
import com.project.service.CategoryService;
import com.project.service.PostService;

public class PostListServlet extends HttpServlet{
	private PostService postService = new PostService();
	private CategoryService categoryService = new CategoryService();
	
	@Override 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		String categoryId = request.getParameter("categoryId");
		System.out.println("categoryId=========" + categoryId);
		Category category = categoryService.getCategoryById(categoryId);
		List<PostDTO> postDTOList = postService.getPostDTOListByCategory(categoryId);
		System.out.println(postService + "-------------------" + postDTOList);
		request.setAttribute("postDTOList", postDTOList);
		request.setAttribute("category", category);
		request.getRequestDispatcher("/WEB-INF/view/postlist.jsp").forward(request, response);
	}
}
