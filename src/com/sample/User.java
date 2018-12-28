package com.sample;

public class User 
{
	private String username;
	private String password;
	private String email;
	private String firstName;
	private String lastName;
	
	//Setters
	public void setFirstName(String firstName)
	{
		this.firstName = firstName;
	}
	
	public void setLastName(String lastName)
	{
		this.lastName = lastName;
	}
	
	public void setEmail(String email)
	{
		this.email = email;
	}
	
	public void setUsername(String username)
	{
		this.username = username;
	}
	
	public void setPassword(String password)
	{
		this.password = password;
	}
	
	//Getters
	public String getFirstName()
	{
		return firstName;
	}
	
	public String getLastName()
	{
		return lastName;
	}
	
	public String getEmail()
	{
		return email;
	}
	
	public String getUsername()
	{
		return username;
	}
	
	public String getPasswrod()
	{
		return password;
	}
}
