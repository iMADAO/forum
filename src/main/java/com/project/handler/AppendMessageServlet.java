package com.project.handler;

import java.io.File;
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
import com.project.bean.User;
import com.project.service.MessPicService;
import com.project.service.MessageService;
import com.project.util.KeyUtil;

@MultipartConfig
public class AppendMessageServlet extends HttpServlet {
	private MessageService messageService = new MessageService();
	private MessPicService messPicService = new MessPicService();
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String postId = request.getParameter("postId");
		System.out.println(postId);
		request.setAttribute("postId", postId);
		request.getRequestDispatcher("/WEB-INF/view/appendMessage.jsp").forward(request, response);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println(request.getParameter("postId"));
		String postId = request.getParameter("postId");
		System.out.println("append----------postId==" + postId);
     
		User user = (User) request.getSession().getAttribute("user");
		if(user==null){
			response.sendRedirect(request.getContextPath() + "/toLogin");
			return;
		}
		System.out.println(user);
		String messContent = request.getParameter("content");
		System.out.println(messContent);
        request.setCharacterEncoding("UTF-8");
        Part part = request.getPart("uploadFile");
        System.out.println("part-------------------------" + part);
        String inputName=part.getName();
        System.out.println(inputName);
        InputStream input=part.getInputStream();
        //想要保存的目标文件的目录下
        String tagDir=getServletContext().getRealPath("/resource/image/");
        if(!Files.exists(Paths.get(tagDir))){
        	Files.createDirectories(Paths.get(tagDir));
        }
        //避免文件名重复使用uuid来避免,产生一个随机的uuid字符
        String realFileName=KeyUtil.genUniquKey();
        OutputStream output=new FileOutputStream(new File(tagDir,realFileName));
        int len=0;
        byte[] buff=new byte[1024*8];
         
        while ((len = input.read(buff)) > -1) {
            output.write(buff, 0, len);
        }
 
        input.close();
        output.close();
        
        
        System.out.println("postId=======================" + postId);
//                                                         String userId, String userName, String theme, String postId, String messContent
        String messageId = messageService.addMessage(user.getUserId(), user.getUserName(), null, postId, messContent);
        System.out.println("messageId==========" + messageId);
        
        MessPic messPic = new MessPic();
        messPic.setPicId(KeyUtil.genUniquKey());
        messPic.setPicPath("resource/image/" + realFileName);
        messPic.setMessId(messageId);
        messPicService.addMessPic(messPic);
        response.sendRedirect(request.getContextPath() + "/post?postId=" + postId);
	} 

}
