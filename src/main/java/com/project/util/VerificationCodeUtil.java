package com.project.util;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;

public class VerificationCodeUtil {
	//生成验证码,返回字符串和图片的键值对
	public static Map<String, BufferedImage> getVerificationCode(){
		Map<String, BufferedImage> map = new HashMap<String, BufferedImage>();
		final long serialVersionUID = 1L;
		
		int width = 152;
		int height = 40;
		int codeCount = 4;
		
		int fontHeight = 4;
		
		int codeX = 0;
		int codeY = 0;
		
		char [] codeSequence = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz23456789".toCharArray();
		
		fontHeight = height - 2;
		codeX = width / (codeCount + 2);
		codeY = height - 4;

		BufferedImage buffImg = null;
		buffImg = new BufferedImage(width, height, BufferedImage.TYPE_3BYTE_BGR);
	
		Graphics2D graphics = null;
		graphics = buffImg.createGraphics();
		
		graphics.setColor(Color.WHITE);
		
		graphics.fillRect(0, 0, width, height);
		
		Font font = null;
		font = new Font("", Font.BOLD, fontHeight);
		graphics.setFont(font);
		
		graphics.setColor(Color.BLACK);
		
		graphics.drawRect(0, 0, width - 1, height - 1);
		
		Random random = null;
		random = new Random();
		graphics.setColor(Color.GREEN);
		for(int i = 0; i < 55; i++){
			int x = random.nextInt(width);
			int y = random.nextInt(height);
			int x1 = random.nextInt(20);
			int y1 = random.nextInt(20);
			graphics.drawLine(x, y, x + x1, y + y1);
		}
		
		StringBuffer randomCode;
		randomCode = new StringBuffer();
		
		for(int i = 0; i < codeCount; i++){
			String strRand = null;
			strRand = String.valueOf(codeSequence[random.nextInt(36)]);
			
			randomCode.append(strRand);
			graphics.setColor(Color.BLUE);
			graphics.drawString(strRand, (i + 1)* codeX, codeY);
		}
		map.put(randomCode.toString(), buffImg);
		return map;
	}
}
