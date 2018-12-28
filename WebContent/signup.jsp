<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Signup</title>
	<script>
		function checkUsername(str) 
		{
			if(str == "")
			{
				document.getElementById("demo").innerHTML = "";
			}
			else
			{
				var xhttp = new XMLHttpRequest();
		  		xhttp.onreadystatechange = function() 
		  		{
			    	if (this.readyState == 4 && this.status == 200) 
			    	{
				     	document.getElementById("demo").innerHTML = this.responseText;
		    		}
	  			};
				xhttp.open("GET","http://localhost:8080/JSP_Sample/Control?username=" + str, true);
				xhttp.send();
			}
		}
		
		function checkEmail(str) 
		{
			if(str == "")
			{
				document.getElementById("omed").innerHTML = "";
			}
			else
			{
				var xhttp = new XMLHttpRequest();
		  		xhttp.onreadystatechange = function() 
		  		{
			    	if (this.readyState == 4 && this.status == 200) 
			    	{
				     	document.getElementById("omed").innerHTML = this.responseText;
		    		}
	  			};
				xhttp.open("GET","http://localhost:8080/JSP_Sample/Control?email=" + str, true);
				xhttp.send();
			}
		}
	</script>
</head>
<body>
	<h1>Signup Page</h1>
	
	<% session.setAttribute("from","signup"); %>
	
	<form action = "/JSP_Sample/Control" method = "post">
		First Name: <input type = "text" name = "first_name">
		<% if(session.getAttribute("error") == "missing") { %>
		Enter all the details.
		<% } %>
		<br>
		Last Name: <input type = "text" name = "last_name">
		<br>
		Email: &nbsp &nbsp &nbsp &nbsp <input type = "email" name = "email" onchange = "checkEmail(this.value)"><span id="omed"></span>
		<% if(session.getAttribute("error") == "email") { %>
		Email ID already present.
		<% } %>
		<br>
		Username: <input type = "text" name = "user_name" onchange = "checkUsername(this.value)"><span id="demo"></span>
		<% if(session.getAttribute("error") == "username") { %>
		Username already present.
		<% } %>
		<br>
		Password: &nbsp<input type = "password" name = "password">
		<br>
		<br>
		<input type = "submit" name = "signup" value = "Signup" style="height:80px;width:100px">
	</form>
</body>
</html>