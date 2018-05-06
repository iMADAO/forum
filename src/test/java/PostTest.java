import java.util.List;

import org.junit.Test;

import com.project.bean.Post;
import com.project.bean.PostDTO;
import com.project.dao.PostDAO;
import com.project.dao.impl.PostDAOImpl;
import com.project.service.PostService;


public class PostTest {

	private PostDAO postDAO = new PostDAOImpl();
	private PostService postService = new PostService();
	@Test
	public void testGet(){
		Post post = postDAO.getPostById("1525445769438530098");
		System.out.println(post);
	}
	
	@Test
	public void testAdd(){
		Post post = new Post();
		post.setPostId("1");
		post.setThemeId("1002");
		post.setCategoryId("1001");
		postDAO.addPost(post);
	}
	
	@Test
	public void testGetForList(){
		List<Post> postList = postDAO.getPostListByCategoryId("1003");
		for(Post post: postList){
			System.out.println(post);
		}
	}
	
	@Test
	public void testDelete(){
		postDAO.deletePost("1002");
	}
	
	@Test
	public void test(){
		System.out.println(postDAO.getMaxOrderOfPost("p1"));
	}
	
	@Test 
	public void testsetPostVisualState(){
		postDAO.setPostVisualState("1001", (byte)1);
	}
	
	
	@Test
	public void testgetPostDTOListByCategory(){
		List<PostDTO> postDTOList = postService.getPostDTOListByCategory("1001");
		System.out.println(postDTOList.size());
		for(PostDTO postDTO: postDTOList){
			System.out.println(postDTO);
		}
	}
	
	@Test
	public void testgetPostDTOByUserId(){
		List<PostDTO> postDTOList =  postService.getPostDTOByUserId("1001");
		System.out.println(postDTOList.size());
		for(PostDTO postDTO: postDTOList){
			System.out.println(postDTO);
		}
	}
}
