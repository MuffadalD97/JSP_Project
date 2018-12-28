package com.sample;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebFilter("/home.jsp")
public class SessionHomeFilter implements Filter 
{
	private HttpServletRequest req = null;
	private HttpServletResponse res = null;
	private HttpSession session = null;
	private String username = null;

    public SessionHomeFilter() 
    {
    }

	public void destroy() 
	{
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) 
	throws IOException, ServletException 
	{
		req = (HttpServletRequest) request;
		res = (HttpServletResponse) response;
		session = req.getSession();
		username = (String) session.getAttribute("username");
		System.out.println(username + "it is.");
		System.out.println(username == null);
		
		//Redirects user to login page if user hasn't logged in.
		if(username == null)
		{
			res.sendRedirect("http://localhost:8080/JSP_Sample/login.jsp");
		}
		
		//Get data from database to present on home page.
		else
		{
			System.out.println("username is there.");
			User user = new User();
			UserData userData = new UserData();
			user = userData.getUserData(username);
			request.setAttribute("user",user);
			chain.doFilter(request, response);
		}
	}

	public void init(FilterConfig fConfig) throws ServletException {
	}

}
