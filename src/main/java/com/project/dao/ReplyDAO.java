package com.project.dao;

import java.util.List;

import com.project.bean.Reply;

public interface ReplyDAO {
	List<Reply> getReplyListByMessId(String messId);
	void addReply(Reply reply);
	Integer getMaxOrderByMessageId(String string);
}
