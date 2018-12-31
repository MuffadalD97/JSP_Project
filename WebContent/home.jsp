<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import = "com.sample.User" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%! User user = null; String file = "";%>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>Home Page</title>

	<script type="text/javascript" src="handlebars-v4.0.12.js"></script>

	<style>
		.btn-group .button 
		{
			background-color: #4CAF50; /* Green */
			border: none;
			color: white;
			padding: 15px 32px;
			text-align: center;
			text-decoration: none;
			display: inline-block;
			font-size: 16px;
		  	cursor: pointer;
			float: left;
		}
		
		.btn-group .button:hover 
		{
			background-color: #3e8e41;
		}
		
		table, th, td 
		{
  			border: 1px solid black;
		}
	</style>
	
	<!-- Handlebar Template for Leagues -->
	<script id="leagueTemplate" type="text/x-handlebars-template">
        <table>
			<tr>
				<th>
					Name
				</th>
				<th>
					Country
				</th>
				<th>
					Number of Teams
				</th>
				<th>
					Champions League spots
				</th>
			</tr>
			{{#each result}}
			<tr>
				<td>
					{{name}}
				</td>
				<td>
					{{country}}
				</td>
				<td>
					{{no_of_teams}}
				</td>
				<td>
					{{cl_spots}}
				</td>
			</tr>
			{{/each}}
		</table>
	</script>
	
	<!-- Handlebar Template for Teams -->
	<script id="teamTemplate" type="text/x-handlebars-template">
		<table>
			<tr>
				<th>
					Name
				</th>
				<th>
					Manager
				</th>
				<th>
					Stadium
				</th>
				<th>
					Position
				</th>
			</tr>
			{{#each result}}
			<tr>
				<td>
					{{name}}
				</td>
				<td>
					{{manager}}
				</td>
				<td>
					{{stadium}}
				</td>
				<td>
					{{position}}
				</td>
			</tr>
			{{/each}}
		</table>
	</script>
	
	<!-- Handlebar Template for Players -->
	<script id="playerTemplate" type="text/x-handlebars-template">
       	<table>
			<tr>
				<th>
					Name
				</th>
				<th>
					Age
				</th>
				<th>
					Nationality
				</th>
				<th>
					Position
				</th>
			</tr>
			{{#each result}}
			<tr>
				<td>
					{{name}}
				</td>
				<td>
					{{age}}
				</td>
				<td>
					{{nationality}}
				</td>
				<td>
					{{position}}
				</td>
			</tr>
			{{/each}}
		</table>
	</script>
	
	<script type = "text/javascript">
		function buttonSelect(id)
		{
			var str = "leagueTemplate";
			var context;
			var a = document.getElementById("test1").getElementsByTagName("button");
			for (var i=0; i<a.length; i++) 
			{
				a[i].style.backgroundColor = "#4CAF50";
			}
			document.getElementById(id).focus();
			document.getElementById(id).style.backgroundColor = '#277529';
			
			//Setting the right Template based on button clicked
			if(id == "leagues")
				str = "leagueTemplate";
			else if(id == "teams")
				str = "teamTemplate";
			else if(id == "players")
				str = "playerTemplate";	
			
			//AJAX call for retrieving database information synchronously
			var xhttp = new XMLHttpRequest();
			xhttp.onreadystatechange = function() 
			{
			 	if (this.readyState == 4 && this.status == 200) 
			 	{
			 		//Getting response in JSON format
			     	try {
		                var context = JSON.parse(xhttp.responseText);
		            } catch(err) {
		                console.log(err.message + " in " + xmlhttp.responseText);
		                return;
		            };
		            
		            //Compiling and executing the Template
			     	var tmpHtml = document.getElementById(str).innerHTML;
				    var template = Handlebars.compile(tmpHtml);
				    var data = template(context);
				    
				    //Displaying Template here
				    document.getElementById("displayArea").innerHTML = data;
			    }
			};

			//Sending AJAX call to this URL
			xhttp.open("GET", "http://localhost:8080/JSP_Sample/Control?id=" + id, true);
			xhttp.send();
		}
	</script>
	
</head>

<% user = (User)request.getAttribute("user"); %>

<body>

	<form action = "/JSP_Sample/login.jsp" method = "get" align = "right" style = "vertical-align:top;">
		<input type = "submit" name = "logout" value = "Logout" style="height:120px;width:100px">
	</form>
	
	Welcome <%=user.getFirstName()%> <%= user.getLastName()%>,
	<br>
	<br>
	
	<div id = "test1" class="btn-group">
	  <button class="button" id = "leagues" name = "leagues" value = "leagues" autofocus onfocus = "buttonSelect(this.id)" onclick = "buttonSelect(this.id)">Leagues</button>
	  <button class="button" id = "teams" name = "teams" value = "teams" onclick = "buttonSelect(this.id)">Teams</button>
	  <button class="button" id = "players" name = "players" values = "players" onclick = "buttonSelect(this.id)">Players</button>
	</div>
	
	<br>
	<br>
	<br>
	<br>
	
	<div id="displayArea"></div>
	
	<br>
</body>
</html>