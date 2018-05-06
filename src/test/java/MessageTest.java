import java.util.List;

import org.junit.Test;

import com.project.bean.Message;
import com.project.bean.MessageDTO;
import com.project.dao.MessageDAO;
import com.project.dao.impl.MessageDAOImpl;


public class MessageTest {

	private MessageDAO messDAO = new MessageDAOImpl();
	@Test
	public void testAddMess(){
		Message message = new Message();
		message.setMessageId("4");
		message.setPostId("1");
		message.setMessContent("content222");
		message.setPostOrder(2);
		messDAO.addMessage(message);
	}
	
	@Test
	public void testGet(){
		Message message = messDAO.getMessageById("2");
		System.out.println(message);
	}
	
	@Test
	public void testGetForList(){
		List<Message> messageList = messDAO.getMessageByPostId("p1");
		for(Message message: messageList){
			System.out.println(message);
		}
	}
	
	@Test
	public void testDelete(){
		messDAO.deleteMessById("1001");
	}
	
	@Test
	public void testDeleteByPostId(){
		messDAO.deleteMessByPostId("p1");
	}
	
	@Test
	public void testgetMessageDTOByPostIdInOrderOne(){
		System.out.println(messDAO.getMessageDTOByPostIdInOrderOne("1525445894487986043"));
	}
	
	@Test
	public void testGetOneMessageDTO(){
		System.out.println(messDAO.getMessageByPostIdInOrderOne("1525445894487986043"));
	}
}
