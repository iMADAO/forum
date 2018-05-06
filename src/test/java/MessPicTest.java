import java.util.List;

import org.junit.Test;

import com.project.bean.MessPic;
import com.project.dao.MessPicDAO;
import com.project.dao.impl.MessPicDAOImpl;


public class MessPicTest {
	
	private MessPicDAO dao = new MessPicDAOImpl();
	@Test
	public void testgetMessPicList(){
		List<MessPic> picPath =  (List<MessPic>) dao.getMessPicList("3");
		for(MessPic pic: picPath){
			System.out.println(pic);
		}
	}
	
	@Test
	public void testgetMessPicPathList(){
		List<String> pathList = dao.getMessPicPathList("3");
		for(String path: pathList){
			System.out.println(path);
		}
	}
	
	@Test
	public void addMessPic(){
		MessPic messPic = new MessPic();
		messPic.setPicId("id1");
		messPic.setPicPath("/mnt/disk1/abc.gif");
		messPic.setMessId("messId");
		messPic.setPicOrder(1);
		dao.addMessPic(messPic);
	}
}
