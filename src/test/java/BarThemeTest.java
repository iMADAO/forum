import java.lang.reflect.InvocationTargetException;
import java.util.List;

import org.junit.Test;

import com.project.bean.BarTheme;
import com.project.bean.BarThemeDTO;
import com.project.dao.BarThemeDAO;
import com.project.dao.impl.BarThemeDAOImpl;
import com.project.service.ThemeService;


public class BarThemeTest {

	private BarThemeDAO dao = new BarThemeDAOImpl();
	private ThemeService service = new ThemeService();
	@Test
	public void testAdd(){
		BarTheme theme = new BarTheme();
		theme.setId("1001");
		theme.setThemeName("编程");
		dao.addTheme(theme);
	}
	
	@Test
	public void testGet(){
		BarTheme theme = dao.getById("1001");
		System.out.println(theme);
	}
	
	@Test
	public void testGetList(){
		List<BarTheme> themeList = dao.getThemeList();
		for(BarTheme theme: themeList){
			System.out.println(theme);
		}
	}
	
	@Test
	public void testgetBarThemeDTOList(){
		
			List<BarThemeDTO> list = service.getBarThemeDTOList();
			System.out.println("list-size--- " + list.size());
			for(BarThemeDTO dto: list){
				System.out.println(dto);
				System.out.println(dto.getCategoryList().size());
			}
		
	}
}
