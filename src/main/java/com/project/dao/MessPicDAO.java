package com.project.dao;

import java.util.List;

import com.project.bean.MessPic;

public interface MessPicDAO {
	String getMessPicByOrderOne(String messId);

	List<MessPic> getMessPicList(String messageId);
	
	List<String> getMessPicPathList(String messageId);

	void addMessPic(MessPic messPic);

	Integer getMaxOrder(String messId);
}
