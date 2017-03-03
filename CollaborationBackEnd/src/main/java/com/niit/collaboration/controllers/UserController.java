package com.niit.collaboration.controllers;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.niit.collaboration.dao.UserDAO;
import com.niit.collaboration.model.User;

@RestController
public class UserController {
	Logger log = LoggerFactory.getLogger(UserController.class);

	@Autowired
	private UserDAO userDAO;

	@Autowired
	private User user;

	@Autowired
	HttpSession session;

	@GetMapping("/getUserList")
	public ResponseEntity<List<User>> getUserList() throws NullPointerException
	{
			List<User> list = userDAO.getUserList();
			if (list.isEmpty()) 
			{
				user.setErrorCode("100");
				user.setErrorMsg("Users are not available");
			}
			else
			{
				DateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
				for(User user : list)
				{
					user.setErrorCode("200");
					user.setErrorMsg("Success");
					if(user.getDob() != null)
						user.setBirthdate(df.format(user.getDob()));
				}
			}
			return new ResponseEntity<List<User>>(list, HttpStatus.OK);
	}

	@GetMapping("/getUser-{id}")
	public ResponseEntity<User> getUser(@PathVariable("id") String userName) {
		user = userDAO.getUser(userName);

		if (user == null) {
			user = new User();
			user.setErrorCode("404");
			user.setErrorMsg("User " + userName + " is not found.");
		}
		user.setErrorCode("200");
		user.setErrorMsg("User " + userName + " is found.");
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}

	@PostMapping("/login")
	public ResponseEntity<User> validateUser(@RequestBody User user) 
	{
		System.out.println(user.getUsername());
		System.out.println(user.getPassword());
		boolean value = userDAO.validateUser(user.getUsername(), user.getPassword());
		System.out.println(value);
		if (value == false) 
		{
			user = new User();
			user.setErrorCode("404");
			user.setErrorMsg("Wrong username or password.");
		}
		else
		{
			user = userDAO.getUser(user.getUsername());
			session.setAttribute("username", user.getUsername());
			session.setAttribute("user_role", user.getRole());
			user.setErrorCode("200");
			user.setErrorMsg("Success");
		}
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}

	@PostMapping("/add_User")
	public ResponseEntity<User> addUser(@RequestBody User user) 
	{	    
	    user.setStatus('N');
		boolean value = userDAO.addUser(user);
		if (value == true) 
		{
			user.setErrorCode("200");
			user.setErrorMsg("User added Successfully");
		} 
		else 
		{
			user.setErrorCode("100");
			user.setErrorMsg("Add User Failed");
		}
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}

	@GetMapping("/delete_user-{id}")
	public ResponseEntity<User> deleteUser(@PathVariable("id") String userName) 
	{
		User user = userDAO.getUser(userName);
		boolean value = userDAO.deleteUser(user);
		user = new User();
		if (value == true) 
		{
			user.setErrorCode("200");
			user.setErrorMsg("User Deleted Successfully");
		}
		else
		{
			user.setErrorCode("100");
			user.setErrorMsg("Delete User Failed");
		}
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}
	
	@GetMapping("/logout")
	public ResponseEntity<User> logout()
	{
		session.invalidate();
		user.setErrorCode("200");
		user.setErrorMsg("You have logged out.");
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}
}