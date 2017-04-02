package com.niit.collaboration.dao;


import java.util.List;

import com.niit.collaboration.model.BlogComment;

public interface CommentDAO
{
	
	public boolean addBlogComment(BlogComment blogComment);

	public boolean deleteComment(int id);
	
	public List<BlogComment> getBlogComments(String blog_id);
}