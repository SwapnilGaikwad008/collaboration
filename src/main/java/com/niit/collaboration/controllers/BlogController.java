package com.niit.collaboration.controllers;


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
import com.niit.collaboration.dao.BlogDAO;
import com.niit.collaboration.model.Blog;
import com.niit.collaboration.util.Date_Time;

@RestController
public class BlogController 
{
	Logger log = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	private BlogDAO blogDAO;
	
	@Autowired
	private Blog blog;
	
	@Autowired
	private HttpSession session;
	
	@GetMapping("/admin_getAllBlogs")
	public ResponseEntity<List<Blog>> getAllBlogs()
	{
		List<Blog> list = blogDAO.getAllBlogs();
		if(list.isEmpty())
		{
			blog.setErrorCode("100");
			blog.setErrorMsg("No blogs are available");
		}
		
		blog.setErrorCode("200");
		blog.setErrorMsg("Success");
		return new ResponseEntity<List<Blog>>(list, HttpStatus.OK);
	}
	
	@PostMapping("/admin_approveBlog")
	public ResponseEntity<Blog> approveBlog(@RequestBody Blog blog)
	{
		log.info("Blog recieved");
		String title = blog.getBlog_title();
		blog = blogDAO.getBlog(title);
		blog.setStatus("Approved");
		boolean value = blogDAO.approveBlog(blog);
		if(value == true)
		{
			log.info("Blog approved successfully");
			blog.setErrorCode("100");
			blog.setErrorMsg("Blog has been approved");
		}
		else
		{
			log.info("Blog has not been approved");
			blog.setErrorCode("404");
			blog.setErrorMsg("Approving Blog Unsuccessful");
		}
		return new ResponseEntity<Blog>(blog, HttpStatus.OK);
	}
	
	@PostMapping("/admin_rejectBlog")
	public ResponseEntity<Blog> rejectBlog(@RequestBody Blog blog)
	{
		log.info("Blog recieved");
		String title = blog.getBlog_title();
		blog = blogDAO.getBlog(title);
		blog.setStatus("Rejected");
		boolean value = blogDAO.approveBlog(blog);
		if(value == true)
		{
			log.info("Blog rejected successfully");
			blog.setErrorCode("100");
			blog.setErrorMsg("Blog has been rejected");
		}
		else
		{
			log.info("Blog has not been rejected");
			blog.setErrorCode("404");
			blog.setErrorMsg("Rejecting Blog Unsuccessful");
		}
		return new ResponseEntity<Blog>(blog, HttpStatus.OK);
	}
	
	@GetMapping("/viewBlogs")
	public ResponseEntity<List<Blog>> getApprovedBlogs()
	{
		List<Blog> list = blogDAO.getApprovedBlogs();
		blog.setErrorCode("200");
		blog.setErrorMsg("Success");
		return new ResponseEntity<List<Blog>>(list, HttpStatus.OK);
	}
	
	@GetMapping("/viewMyBlogs-{id}")
	public ResponseEntity<List<Blog>> getUserBlogs(@PathVariable("id") String username)
	{
		List<Blog> list = blogDAO.getBlogByUser(username);
		if(list == null)
		{
			blog = new Blog();
			blog.setErrorCode("100");
			blog.setErrorMsg("Users Blogs are not available");
		}
		else
		{
			blog.setErrorCode("200");
			blog.setErrorMsg("Success");
		}
		return new ResponseEntity<List<Blog>>(list, HttpStatus.OK);
	}
	
	@GetMapping("/getBlog-{title}")
	public ResponseEntity<Blog> getBlog(@PathVariable ("title") String title)
	{
		System.out.println("Name - "+title);
		blog = blogDAO.getBlog(title);
		if(blog == null)
		{
			blog = new Blog();
			blog.setErrorCode("100");
			blog.setErrorMsg("Blog is not available");
		}
		else
		{
			blog.setErrorCode("200");
			blog.setErrorMsg("Blog Details retrieved");
		}
		return new ResponseEntity<Blog>(blog, HttpStatus.OK);
	}
	
	@PostMapping("/deleteBlog")
	public ResponseEntity<Blog> deleteBlog(@RequestBody Blog blog)
	{
		System.out.println("Name Recieved - "+blog.getBlog_title());
		String title = blog.getBlog_title();
		blog = blogDAO.getBlog(title);
		boolean value = blogDAO.deleteBlog(blog);
		if(value == true)
		{
			blog = new Blog();
			blog.setErrorCode("100");
			blog.setErrorMsg("Blog has been deleted");
		}
		else
		{
			blog.setErrorCode("404");
			blog.setErrorMsg("Delete Unsuccessful");
		}
		return new ResponseEntity<Blog>(blog, HttpStatus.OK);
	}
	
	@PostMapping("/addBlog")
	public ResponseEntity<Blog> addBlog(@RequestBody Blog blog)
	{
		log.info("Blog Recieved");
		blog.setStatus("Submitted");
		blog.setUsername(session.getAttribute("username").toString());
		Date_Time dt = new Date_Time();
		String date = dt.getDateTime();
		blog.setDate_time(date);
		boolean value = blogDAO.addBlog(blog);
		
		if(value == true)
		{
			log.info("Blog added successfully");
			blog.setErrorCode("100");
			blog.setErrorMsg("Blog has been added");
		}
		else
		{
			log.info("Blog has not been added");
			blog.setErrorCode("404");
			blog.setErrorMsg("Adding Blog Unsuccessful");
		}
		return new ResponseEntity<Blog>(blog, HttpStatus.OK);
	}
	
	@PostMapping("/updateBlog")
	public ResponseEntity<Blog> updateBlog(@RequestBody Blog blog)
	{
		log.info("Blog Recieved");
		
		Date_Time dt = new Date_Time();
		String date = dt.getDateTime();
		blog.setDate_time(date);
		blog.setStatus("Updated");
		boolean value = blogDAO.updateBlog(blog);
		if(value == true)
		{
			log.info("Blog updated successfully");
			blog.setErrorCode("100");
			blog.setErrorMsg("Blog has been updated");
		}
		else
		{
			log.info("Blog has not been updated");
			blog.setErrorCode("404");
			blog.setErrorMsg("Updating Blog Unsuccessful");
		}
		return new ResponseEntity<Blog>(blog, HttpStatus.OK);
	}
}