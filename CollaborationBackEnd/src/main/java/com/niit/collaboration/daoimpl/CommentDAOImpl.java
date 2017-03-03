package com.niit.collaboration.daoimpl;


import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;
import com.niit.collaboration.dao.CommentDAO;
import com.niit.collaboration.model.BlogComment;

@Repository("commentDAO")
@EnableTransactionManagement
public class CommentDAOImpl implements CommentDAO
{
	private static final Logger log = LoggerFactory.getLogger(CommentDAOImpl.class);
	
	@Autowired
	SessionFactory sessionFactory;
	
	public CommentDAOImpl(SessionFactory sessionFactory) 
	{
		try
		{
			this.sessionFactory = sessionFactory;
			log.info("Connection Established Successfully");
		}
		catch(Exception ex)
		{
			log.error("Failed to establish connection");
			ex.printStackTrace();
		}
	}

	@Transactional
	public boolean addBlogComment(BlogComment blogComment) 
	{
		try
		{
			sessionFactory.getCurrentSession().save(blogComment);
			log.info("Saved Comment successfully");
			return true;
		}	catch(HibernateException ex)
		{
			log.error("Failed to add comment");
			ex.printStackTrace();
			return false;
		}
		
	}

	@Transactional
	public boolean deleteComment(BlogComment blogComment) 
	{
		return false;
	}

	@Transactional
	public List<BlogComment> getBlogComments(int blog_id) 
	{
		return null;
	}
	
}
