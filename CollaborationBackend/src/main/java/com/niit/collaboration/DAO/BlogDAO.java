package com.niit.collaboration.DAO;

import java.util.List;

import com.niit.collaboration.model.Blog;

public interface BlogDAO {
	
	public boolean SaveBlog(Blog blog);
	
	public boolean Update(Blog blog);
	
	public boolean DeleteBlog(Blog blog);
	
	public Blog getid(int id);
	
	public Blog getByid(int id);
	
	public List<Blog> list();
}
