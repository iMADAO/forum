package com.project.dao.impl;

import java.util.ArrayList;
import java.util.List;

import com.project.bean.MessPic;
import com.project.dao.MessPicDAO;

public class MessPicDAOImpl extends DAOImpl<MessPic> implements MessPicDAO {

	public String getMessPicByOrderOne(String messId) {
		String sql = "select pic_path from mess_pic where mess_id = ? and pic_order=1";
		System.out.println(messId);
		String picPath = (String) getForValue(sql, messId);
		return picPath;
	}

	public List<String> getMessPicPathList(String messageId) {
		List<MessPic> messPicList = getMessPicList(messageId);
		List<String> path = new ArrayList<String>();
		for(MessPic messPic: messPicList){
			path.add(messPic.getPicPath());
		}
		return path;
	}

	public List<MessPic> getMessPicList(String messageId) {
		String sql = "select pic_id picId, pic_path picPath, mess_id messId, pic_order picOrder from mess_pic where mess_id = ?";
		List<MessPic> messPicList = getForList(sql, messageId);
		return messPicList;
	}

	public void addMessPic(MessPic messPic) {
		String sql = "insert into mess_pic (pic_id, pic_path, mess_id, pic_order) values(?, ?, ?, ?)";
		update(sql, messPic.getPicId(), messPic.getPicPath(), messPic.getMessId(), messPic.getPicOrder());
	}

	public Integer getMaxOrder(String messId) {
		String sql = "select max(pic_order) from mess_pic where mess_id = ?";
		return getForValue(sql, messId);
	}
	
	
}
