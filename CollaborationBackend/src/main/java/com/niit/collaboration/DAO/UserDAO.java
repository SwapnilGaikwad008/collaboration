package com.niit.collaboration.DAO;

import java.util.List;

import com.niit.collaboration.model.User;

public interface UserDAO {
   
   public boolean SaveUser(User user);
	
	public boolean UpdateUser(User user);
	
	public boolean DeleteUser(User user);
	
	public User getById(String emailId);
	
	public List<User > list();
	
	public List<User> getAllList(String name);
	
	public void setOnline(String name);
	
	public void setOffline(String name);
	
	public User isValidUser(String emailid, String password);
	

}
