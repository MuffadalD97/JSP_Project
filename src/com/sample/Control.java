package com.sample;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet("/Control")
public class Control extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
	private HttpSession session = null;
	private String from = null,username = null,email = null;
	private User user = null;
	private UserData userData = null;
	PrintWriter out = null;
       
    public Control() 
    {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
	throws ServletException, IOException 
	{
		userData = new UserData();
		username = request.getParameter("username");
		email = request.getParameter("email");
		out = response.getWriter();
		
		//AJAX call to check if username is already present.		
		if(request.getParameter("username") != null && userData.checkUsername(username))
		{
			out.print(" <br><p><b>Username is already present.</b></p>");
		}
		
		//AJAX call to check if email id is already present.
		if(request.getParameter("email") != null && userData.checkEmail(email))
		{
			out.print(" Email is already present.");
		}
		
		if(request.getParameter("id") != null)
		{
			System.out.println(request.getParameter("id"));
			response.setContentType("application/json");
			out = response.getWriter();
			out.print(userData.getTableData(request.getParameter("id")));
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
	throws ServletException, IOException 
	{
		session = request.getSession();
		from = (String) session.getAttribute("from");
		user = new User();
		userData = new UserData();
		
		if(from == "login")
		{
			user.setUsername(request.getParameter("user_name"));
			user.setPassword(request.getParameter("password"));
			
			//Checking if login details have been correctly entered.
			if(userData.authenticate(user))
			{
				session.setAttribute("username",request.getParameter("user_name"));
				response.sendRedirect("http://localhost:8080/JSP_Sample/home.jsp");
			}
			else
			{
				System.out.println("Not Authenticated.");
				session.setAttribute("error","login");
				response.sendRedirect("http://localhost:8080/JSP_Sample/login.jsp");
			}
		}
		
		else if(from == "signup")
		{
			user.setFirstName(request.getParameter("first_name"));
			user.setLastName(request.getParameter("last_name"));
			user.setEmail(request.getParameter("email"));
			user.setUsername(request.getParameter("user_name"));
			user.setPassword(request.getParameter("password"));
			System.out.println(request.getParameter("first_name"));
			
			//Checking if all data is entered.
			if(!userData.isDataPresent(user))
			{
				System.out.println("Data Missing");
				session.setAttribute("error","missing");
				response.sendRedirect("http://localhost:8080/JSP_Sample/signup.jsp");
			}
			
			//Checking if email id is already present or not.
			else if(userData.checkEmail(user))
			{
				System.out.println("Email Present");
				session.setAttribute("error","email");
				response.sendRedirect("http://localhost:8080/JSP_Sample/signup.jsp");
			}
			
			//Checking if username is already present or not.
			else if(userData.checkUsername(user))
			{
				System.out.println("User Present");
				session.setAttribute("error","username");
				response.sendRedirect("http://localhost:8080/JSP_Sample/signup.jsp");
			}
			
			//Insert data into database if all details have been entered correctly.
			else
			{
				userData.insertData(user);
				session.setAttribute("account","created");
				response.sendRedirect("http://localhost:8080/JSP_Sample/login.jsp");
			}
		}
	}
}
