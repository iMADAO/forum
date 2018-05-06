package com.project.service;

import java.util.List;

import com.project.bean.MessPic;
import com.project.dao.MessPicDAO;
import com.project.dao.impl.MessPicDAOImpl;

public class MessPicService {
	private MessPicDAO messPicDAO = new MessPicDAOImpl();
	public void addMessPic(MessPic messPic){
		Integer maxOrder = messPicDAO.getMaxOrder(messPic.getMessId());
		if(maxOrder==null){
			messPic.setPicOrder(1);
		}else{
			messPic.setPicOrder(maxOrder+1);
		}
		messPicDAO.addMessPic(messPic);
	}
	
	public String getMessPicByOrderOne(String messId){
		return messPicDAO.getMessPicByOrderOne(messId);
	}
	
	public List<String> getMessagePicPathList(String messageId){
		return messPicDAO.getMessPicPathList(messageId);
	}
}
