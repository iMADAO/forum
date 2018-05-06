package com.project.dao.impl;

import java.util.List;

import com.project.bean.Reply;
import com.project.dao.ReplyDAO;

public class ReplyDAOImpl extends DAOImpl<Reply> implements ReplyDAO {

	public List<Reply> getReplyListByMessId(String messId) {
		String sql = "select reply_id replyId, user_id userId, user_name username, mess_id messId, reply_content replyContent, reply_order replyOrder, create_time createTime, update_time updateTime from reply where mess_id = ?";
		List<Reply> replyList = getForList(sql, messId);
		return replyList;
	}
	
	public void addReply(Reply reply){
		String sql = "insert into reply(reply_id, user_id, user_name, mess_id, reply_content, reply_order) values(?, ?, ?, ?, ?, ?)";
		update(sql, reply.getReplyId(), reply.getUserId(), reply.getUsername(), reply.getMessId(), reply.getReplyContent(), reply.getReplyOrder());
	}

	public Integer getMaxOrderByMessageId(String messId) {
		String sql = "select max(reply_order) from reply where mess_id = ?";
		return getForValue(sql, messId);
		
	}

}
