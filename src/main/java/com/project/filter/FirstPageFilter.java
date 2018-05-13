package com.project.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.project.bean.User;
import com.project.service.UserService;

/**
 * Servlet Filter implementation class FirstPageFilter
 */
public class FirstPageFilter implements Filter {
	private UserService service = new UserService();
    /**
     * Default constructor. 
     */
    public FirstPageFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		// place your code here

		// pass the request along the filter chain
		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
		HttpServletResponse httpServletResponse = (HttpServletResponse) response;
		HttpSession session = httpServletRequest.getSession();
		if(session.getAttribute("user")==null){
			Cookie[] cookies = httpServletRequest.getCookies();
			String username = null;
			String password =  null;
		
			System.out.println(cookies);
			if(cookies!=null){
				for(Cookie cookie: cookies){
					if(cookie.getName().equals("username"))
						username = cookie.getValue();
					else if(cookie.getName().equals("password")){
						password = cookie.getValue();
					}
				}
			}
			
			System.out.println("username----" + username + "password----" + password);
			if(username!=null && username!="" && password!=null && password!=""){
				if(service.verifyPasswd(username, password)){
					User user = service.getUserByName(username);
					user.setPassword("");
					session.setAttribute("user", user);
					String lastPage = (String) session.getAttribute("lastPage");
					if(lastPage==null || lastPage=="")
						lastPage = httpServletRequest.getContextPath() + "/index.jsp";
					httpServletResponse.sendRedirect(lastPage);
					return;
				}
			}
		}
		chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
