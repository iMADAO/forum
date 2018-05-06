package com.project.handler;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.project.bean.MessPic;
import com.project.bean.Message;
import com.project.bean.Post;
import com.project.bean.User;
import com.project.dao.MessPicDAO;
import com.project.dao.MessageDAO;
import com.project.dao.PostDAO;
import com.project.dao.impl.MessPicDAOImpl;
import com.project.dao.impl.MessageDAOImpl;
import com.project.dao.impl.PostDAOImpl;
import com.project.service.MessPicService;
import com.project.service.MessageService;
import com.project.service.PostService;
import com.project.util.KeyUtil;
@MultipartConfig
public class AddPostServlet extends HttpServlet{
	private MessPicService messPicService = new MessPicService();
	private MessageService messageService = new MessageService();
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		User user = (User) request.getSession().getAttribute("user");
		if(user==null){
			request.getSession().setAttribute("tipMess", "用户未登录");
			response.sendRedirect(request.getContextPath() + "/toLogin");
			return;
		}
		request.getRequestDispatcher("/WEB-INF/view/addPost.jsp").forward(request, response);
	}
	
//	@Override
//	protected void doPost(HttpServletRequest request, HttpServletResponse response)
//			throws ServletException, IOException {
//		String target = request.getParameter("target");
//		System.out.println(target);
//		
//		InputStream in = request.getInputStream();
//		System.out.println(in.available());
//		String pathStr = request.getServletContext().getRealPath("/") + "/resource/image/";
//		if(Files.notExists(Paths.get(pathStr))){
//			Files.createDirectories(Paths.get(pathStr));
//			System.out.println("Create Directory");
//		}
//		pathStr += KeyUtil.genUniquKey() + ".jpg";
//		System.out.println(pathStr);
//		
//		FileOutputStream fout = new FileOutputStream(new File(pathStr));
//		int n = in.available();
//		byte b[] = new byte[n];
//		in.read(b);
//		fout.write(b);
//		fout.flush();
//		fout.close();
//		in.close();
//		
//	}
	
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		User user = (User) request.getSession().getAttribute("user");
		if(user==null){
			request.getSession().setAttribute("tipMess", "用户未登录");
			response.sendRedirect(request.getContextPath() + "/toLogin");
			return;
		}
		String messContent = request.getParameter("content");
		
		String categoryId = request.getParameter("categoryId");
		System.out.println(categoryId);
		System.out.println(messContent);
		
		if(messContent==null || messContent.equals("")){
			request.getSession().setAttribute("tipMess", "内容不能为空");
			response.sendRedirect(request.getContextPath() + "/addPost?categoryId=" + categoryId);
			return;
		}
		
		String messageId = messageService.addFirstMessage(user.getUserId(), user.getUserName(), null, categoryId, messContent);
        System.out.println("messageId==========" + messageId);
		
        request.setCharacterEncoding("UTF-8");
        Part part = request.getPart("uploadFile");
        if(part.getSize()>0){
	        String inputName=part.getName();
	        System.out.println(request.getAttribute("uploadFile"));
	        System.out.println("inputname----------------------" + inputName);
	        InputStream input=part.getInputStream();
	        System.out.println("inputStream-------------------" + input);
	        //想要保存的目标文件的目录下
	        String tagDir=getServletContext().getRealPath("/resource/image/");
	        if(!Files.exists(Paths.get(tagDir))){
	        	Files.createDirectories(Paths.get(tagDir));
	        }
	        String realFileName=KeyUtil.genUniquKey();
	        OutputStream output=new FileOutputStream(new File(tagDir,realFileName));
	        int len=0;
	        byte[] buff=new byte[1024*8];
	         
	        while ((len = input.read(buff)) > -1) {
	            output.write(buff, 0, len);
	        }
	 
	        input.close();
	        output.close();
	        
	        MessPic messPic = new MessPic();
	        messPic.setPicId(KeyUtil.genUniquKey());
	        messPic.setPicPath("resource/image/" + realFileName);
	        messPic.setMessId(messageId);
	        messPicService.addMessPic(messPic);
        }
        request.getSession().setAttribute("tipMess", "添加成功!");
        response.sendRedirect(request.getContextPath() + "/postList?categoryId=" + categoryId);
    }
}
