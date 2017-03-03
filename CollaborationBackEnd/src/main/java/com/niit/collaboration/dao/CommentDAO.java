package com.niit.collaboration.dao;


import java.util.List;

import com.niit.collaboration.model.BlogComment;

public interface CommentDAO
{
	
	public boolean addBlogComment(BlogComment blogComment);

	public boolean deleteComment(BlogComment blogComment);
	
	public List<BlogComment> getBlogComments(int blog_id);
}