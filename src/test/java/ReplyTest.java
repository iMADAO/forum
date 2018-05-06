import java.util.List;

import org.junit.Test;

import com.project.bean.Reply;
import com.project.dao.ReplyDAO;
import com.project.dao.impl.ReplyDAOImpl;



public class ReplyTest {
	private ReplyDAO dao = new ReplyDAOImpl();
	@Test
	public void testAdd(){
		Reply reply = new Reply();
		reply.setMessId("3");
		reply.setReplyContent("回复1");
		reply.setReplyId("1");
		reply.setReplyOrder(1);
		reply.setUserId("1001");
		dao.addReply(reply);
	}
	
	@Test
	public void testGet(){
		List<Reply> replyList = dao.getReplyListByMessId("3");
		for(Reply reply: replyList){
			System.out.println(reply);
		}
	}
}
