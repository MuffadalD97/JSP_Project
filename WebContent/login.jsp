<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Login</title>
</head>
<body>
	<h1>Login Page</h1>
	
	<% session.setAttribute("from","login"); %>
	
	<form action = "/JSP_Sample/Control" method = "post">
		Username: <input type = "text" name = "user_name">
		<% if(session.getAttribute("account") == "created") { %>
		Account created succesfully.
		<% } %>
		<% if(session.getAttribute("error") == "login") { session.setAttribute("error",null);%>
		Incorrect Username or Password.
		<% } %>
		<br>
		Password: &nbsp<input type = "password" name = "password">
		<br>
		<br>
		<input type = "submit" name = "login" value = "Login" style="height:80px;width:100px">
		<input type = "submit" formaction = "/JSP_Sample/signup.jsp" name = "signup" value = "Signup" style="height:80px;width:100px">
	</form>
</body>
</html>