package com.niit.collaboration.controllers;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.niit.collaboration.DAO.BlogDAO;
import com.niit.collaboration.model.Blog;


@RestController
public class BlogController {

	
	@Autowired
	private BlogDAO blogDAO;
	
	@Autowired
	private Blog blog;
	
	@GetMapping("/admin_getAllBlogs")
	public ResponseEntity<List<Blog>> getAllBlogs()
	{
		List<Blog> list = blogDAO.getAllBlogs();
		if(list.isEmpty())
		{
			blog.setErrorCode("100");
			blog.setErrorMessage("No blogs are available");
		}
		blog.setErrorCode("200");
		blog.setErrorMessage("Success");
		return new ResponseEntity<List<Blog>>(list,HttpStatus.OK);
	}

	@GetMapping("/viewBlogs")
	public ResponseEntity<List<Blog>> getApprovedBlogs()
	{
		List<Blog> list = blogDAO.getApprovedBlogs();
		blog.setErrorCode("200");
		blog.setErrorMessage("Success");
		return new ResponseEntity<List<Blog>>(list, HttpStatus.OK);
	}
	
	@GetMapping("/viewMyBlogs-{id}")
	public ResponseEntity<List<Blog>> getUserBlogs(@PathVariable("id")String email)
	{
		List<Blog> list = blogDAO.getBlogByUser(email);
		if(list == null){
			blog = new Blog();
			blog.setErrorCode("100");
			blog.setErrorMessage("Users Blogs are not available");
		}
		blog.setErrorCode("200");
		blog.setErrorMessage("Success");
		return new ResponseEntity<List<Blog>>(list, HttpStatus.OK);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
}
