package com.sample;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebFilter("/login.jsp")
public class SessionFilter implements Filter 
{
	private HttpSession session = null;
	private HttpServletRequest req = null;
	private HttpServletResponse res = null;

    public SessionFilter() 
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
		session = req.getSession(true);
		
		//Checking if user has logged out. Session is closed if so.
		if("Logout".equals((String)request.getParameter("logout")))
		{
			System.out.println("logged out");
			session.invalidate();
			session = req.getSession(true);
		}
		
		//Checking if session is new.
		else if(session.getAttribute("username") == null)
		{
			System.out.println("Session is new");
		}
		
		//If session is already present then redirect user to home page.
		else
		{
			res.sendRedirect("http://localhost:8080/JSP_Sample/home.jsp");
		}
		//System.out.println("Filter working");
		
		chain.doFilter(request, response);
		
		//System.out.println("Postprocessing");
	}

	public void init(FilterConfig fConfig) throws ServletException 
	{
	}

}
