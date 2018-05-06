package com.project.handler;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.project.util.VerificationCodeUtil;

public class VerificationCodeServlet extends HttpServlet {
	
	final String CHECK_CODE_KEY = "CHECK_CODE_KEY";
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException{
		System.out.println("getValidateCode");
		//获取验证码值和图片的键值对
		Map<String, BufferedImage> resultMap = VerificationCodeUtil.getVerificationCode();
		String verificationCode = null;
		BufferedImage buffImg = null;
		for(Map.Entry<String, BufferedImage> entry: resultMap.entrySet()){
			verificationCode = entry.getKey();
			buffImg = entry.getValue();
		}
		
		request.getSession().setAttribute(CHECK_CODE_KEY, verificationCode);
		System.out.println(verificationCode);
		
		response.setHeader("Pragma", "no-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);

		ServletOutputStream sos = null;
		sos = response.getOutputStream();
		ImageIO.write(buffImg, "jpeg", sos);
		sos.close();
	}
}
