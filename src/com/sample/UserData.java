package com.sample;

import java.sql.*;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class UserData 
{
	//  Database Information
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
	static final String DB_URL = "jdbc:mysql://localhost/sample";

	//  Database credentials
	static final String USER = "root";
	static final String PASS = "";
	
	private Connection conn = null;
	
	public UserData()
	{
		try 
		{
			Class.forName("com.mysql.jdbc.Driver");
			
			System.out.println("Connecting to database...");
			conn = DriverManager.getConnection(DB_URL,USER,PASS);
		} 
		catch (ClassNotFoundException e) 
		{
			e.printStackTrace();
		}
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
	}
	
	//Checks if login details have been correctly entered.
	public boolean authenticate(User user)
	{
		Statement stmt = null;
		ResultSet rs = null;
		try 
		{
			stmt = conn.createStatement();
			String sql = "SELECT * FROM users WHERE username = '" + user.getUsername() + "'" +
											   "and password = '" + user.getPasswrod() + "';";
			rs = stmt.executeQuery(sql);
			if(rs.next())
			{
				System.out.println(rs.getString("username"));
				return true;
			}
			else
				return false;
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
			return false;
		}
		finally
		{
			try
			{
				if(rs != null)
					rs.close();
				if(stmt != null)
					stmt.close();
			} 
			catch (SQLException e) 
			{
				e.printStackTrace();
			}
		}
	}
	
	//Retrieves user data from DB.
	public User getUserData(String username)
	{
		User user = new User();
		Statement stmt = null;
		ResultSet rs = null;
		try 
		{
			stmt = conn.createStatement();
			String sql = "SELECT * FROM users WHERE username = '" + username + "';";
			rs = stmt.executeQuery(sql);
			rs.next();
			System.out.println(rs.isFirst());
			System.out.println(sql);
			user.setFirstName(rs.getString("first_name"));
			user.setLastName(rs.getString("last_name"));
			user.setEmail(rs.getString("email"));
			user.setPassword(rs.getString("password"));
			user.setUsername(rs.getString("username"));
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		finally
		{
			try
			{
				if(rs != null)
					rs.close();
				if(stmt != null)
					stmt.close();
			} 
			catch (SQLException e) 
			{
				e.printStackTrace();
			}
		}
		return user;
	}
	
	//Checks if user is present.
	public boolean checkUsername(User user)
	{
		Statement stmt = null;
		ResultSet rs = null;
		try 
		{
			stmt = conn.createStatement();
			String sql = "SELECT * FROM users WHERE username = '" + user.getUsername() + "';";
			rs = stmt.executeQuery(sql);
			if(rs.next())
			{
				System.out.println(rs.getString("username"));
				return true;
			}
			else
				return false;
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
			return false;
		}
		finally
		{
			try
			{
				if(rs != null)
					rs.close();
				if(stmt != null)
					stmt.close();
			} 
			catch (SQLException e) 
			{
				e.printStackTrace();
			}
		}
	}
	
	//Checks if email is present.
	public boolean checkEmail(User user)
	{
		Statement stmt = null;
		ResultSet rs = null;
		try 
		{
			stmt = conn.createStatement();
			String sql = "SELECT * FROM users WHERE email = '" + user.getEmail() + "';";
			rs = stmt.executeQuery(sql);
			if(rs.next())
			{
				System.out.println(rs.getString("email"));
				return true;
			}
			else
				return false;
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
			return false;
		}
		finally
		{
			try
			{
				if(rs != null)
					rs.close();
				if(stmt != null)
					stmt.close();
			} 
			catch (SQLException e) 
			{
				e.printStackTrace();
			}
		}
	}
	
	public boolean checkUsername(String username)
	{
		Statement stmt = null;
		ResultSet rs = null;
		try 
		{
			stmt = conn.createStatement();
			String sql = "SELECT * FROM users WHERE username = '" + username + "';";
			rs = stmt.executeQuery(sql);
			if(rs.next())
			{
				System.out.println(rs.getString("username"));
				return true;
			}
			else
				return false;
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
			return false;
		}
		finally
		{
			try
			{
				if(rs != null)
					rs.close();
				if(stmt != null)
					stmt.close();
			} 
			catch (SQLException e) 
			{
				e.printStackTrace();
			}
		}
	}
	
	public boolean checkEmail(String email)
	{
		Statement stmt = null;
		ResultSet rs = null;
		try 
		{
			stmt = conn.createStatement();
			String sql = "SELECT * FROM users WHERE email = '" + email + "';";
			rs = stmt.executeQuery(sql);
			if(rs.next())
			{
				System.out.println(rs.getString("email"));
				return true;
			}
			else
				return false;
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
			return false;
		}
		finally
		{
			try
			{
				if(rs != null)
					rs.close();
				if(stmt != null)
					stmt.close();
			} 
			catch (SQLException e) 
			{
				e.printStackTrace();
			}
		}
	}
	
	//Inserts data of new user into DB.
	public void insertData(User user)
	{
		Statement stmt = null;
		try 
		{
			stmt = conn.createStatement();
			String sql = "INSERT INTO users VALUES('" + user.getFirstName() + "',"
												+ "'" + user.getLastName() +"',"
												+ "'" + user.getEmail() + "',"
												+ "'" + user.getUsername() + "',"
												+ "'" + user.getPasswrod() + "');";
			stmt.executeUpdate(sql);
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		finally
		{
			try
			{
				if(stmt != null)
					stmt.close();
			} 
			catch (SQLException e) 
			{
				e.printStackTrace();
			}
		}
	}
	
	public JSONObject getTableData(String tableName)
	{
		int index = 0;
		Statement stmt = null;
		ResultSet rs = null;
		JSONObject jsonObject = null;
		JSONArray jsonArray = new JSONArray();
		JSONObject result = new JSONObject();
		try 
		{
			stmt = conn.createStatement();
			System.out.println(tableName + " table");
			
			if(tableName.equals("leagues"))
			{
				String sql = "SELECT * FROM leagues;";
				System.out.println(sql);
				rs = stmt.executeQuery(sql);
				while(rs.next())
				{
					jsonObject = new JSONObject();
					try {
						jsonObject.put("name",rs.getString("name"));
						jsonObject.put("country",rs.getString("country"));
						jsonObject.put("no_of_teams",rs.getInt("no_of_teams"));
						jsonObject.put("cl_spots",rs.getInt("cl_spots"));
						result.accumulate("result",jsonObject);
					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				System.out.println(result);
			}
			
			else if(tableName.equals("teams"))
			{
				String sql = "SELECT * FROM teams;";
				rs = stmt.executeQuery(sql);
				while(rs.next())
				{
					jsonObject = new JSONObject();
					try {
						jsonObject.put("name",rs.getString("name"));
						jsonObject.put("manager",rs.getString("manager"));
						jsonObject.put("stadium",rs.getString("stadium"));
						jsonObject.put("position",rs.getInt("position"));
						result.accumulate("result",jsonObject);
					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				System.out.println(result);
			}
			
			else if(tableName.equals("players"))
			{
				String sql = "SELECT * FROM players;";
				rs = stmt.executeQuery(sql);
				while(rs.next())
				{
					jsonObject = new JSONObject();
					try {
						jsonObject.put("name",rs.getString("name"));
						jsonObject.put("age",rs.getInt("age"));
						jsonObject.put("nationality",rs.getString("nationality"));
						jsonObject.put("position",rs.getString("position"));
						result.accumulate("result",jsonObject);
					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				System.out.println(result);
			}
			
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		finally
		{
			try
			{
				if(rs != null)
					rs.close();
				if(stmt != null)
					stmt.close();
			} 
			catch (SQLException e) 
			{
				e.printStackTrace();
			}
		}
		return result;
	}
	
	//Checks if all data has been entered on signup page.
	public boolean isDataPresent(User user)
	{
		if(user.getFirstName() != "" && user.getLastName() != "" && user.getEmail() != "" 
				&& user.getUsername() != "" && user.getPasswrod() != "")
			return true;
		else
			return false;
	}
	
	public void finalize()
	{
		try 
		{
			conn.close();
		} catch (SQLException e) 
		{
			e.printStackTrace();
		}
	}
}
