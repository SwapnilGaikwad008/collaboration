package com.niit.collaboration.daoimpl;


import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;
import com.niit.collaboration.dao.BlogDAO;
import com.niit.collaboration.model.Blog;

@SuppressWarnings("deprecation")
@Repository("blogDAO")
@EnableTransactionManagement
public class BlogDAOImpl implements BlogDAO
{
private static final Logger log = LoggerFactory.getLogger(BlogDAOImpl.class);
	
	@Autowired
	private SessionFactory sessionFactory;
	
	public BlogDAOImpl(SessionFactory sessionFactory) 
	{
		try 
		{
			this.sessionFactory = sessionFactory;
			log.info("Connection Established Successfully");
		} 
		catch (Exception ex) 
		{
			log.error("Failed to establish connection");
			ex.printStackTrace();
		}
	}
	
	@Transactional
	public boolean addBlog(Blog blog)
	{
		log.info("Add blog started");
		try
		{
			blog.setStatus("Submitted");
			sessionFactory.getCurrentSession().saveOrUpdate(blog);
			log.info("Add Blog Success");
			return true;
		} catch(Exception ex)
		{
			log.info("Error adding Blog");
			return false;
		}
	}
	
	@Transactional
	public boolean updateBlog(Blog blog)
	{
		log.info("Update Blog by user Started");
		Blog saveBlog = blog;
		{
			String title = saveBlog.getBlog_title();
			String hql_string = "FROM Blog WHERE blog_title = '"+title+"'";
			@SuppressWarnings("rawtypes")
			Query query = sessionFactory.getCurrentSession().createQuery(hql_string);
			blog = (Blog) query.uniqueResult();
			sessionFactory.getCurrentSession().delete(blog);
			log.info("Processing Requests");
		}
		
		try
		{
			saveBlog.setStatus("Updated");
			sessionFactory.getCurrentSession().save(saveBlog);
			log.info("Blog update Success");
			return true;
		} catch(Exception ex)
		{
			ex.printStackTrace();
			log.info("Error adding Blog");
			return false;
		}
	}
	
	@Transactional
	public boolean approveBlog(Blog blog) 
	{
		// TRY THIS WITH SQL IF POSSIBLE
		log.info("Approve Blog by Admin - Started");
		Blog saveBlog = blog;
		{
			String title = saveBlog.getBlog_title();
			String hql_string = "FROM Blog WHERE blog_title = '"+title+"'";
			@SuppressWarnings("rawtypes")
			Query query = sessionFactory.getCurrentSession().createQuery(hql_string);
			blog = (Blog) query.uniqueResult();
			System.out.println(" What ?? "+blog.getBlog_id()+" "+blog.getBlog_title()+" "+blog.getStatus());
			sessionFactory.getCurrentSession().delete(blog);
			log.info("Processing Request");
		}
		
		try
		{
			sessionFactory.getCurrentSession().save(saveBlog);
			log.info("Blog updated Success");
			return true;
		} catch(Exception ex)
		{
			ex.printStackTrace();
			log.info("Error adding Blog");
			return false;
		}
	}

	@Transactional
	public boolean deleteBlog(Blog blog) 
	{
		log.info("Delete Blog method initiated");
		try
		{
			sessionFactory.getCurrentSession().delete(blog);
			log.info("Delete Blog success");
			return true;
		} catch(Exception ex)
		{
			log.info("Error adding Blog");
			return false;
		}
	}

	@Transactional
	public Blog getBlog(String title) 
	{
		log.info("Get Blog method started");
		try
		{
			String hql_string = "FROM Blog WHERE blog_title = '"+title+"'";
			@SuppressWarnings("rawtypes")
			Query query = sessionFactory.getCurrentSession().createQuery(hql_string);
			log.info("Blog Retrieved");
			return (Blog) query.uniqueResult();
		} catch(Exception ex)
		{
			log.info("Error Getting Blog");
			ex.printStackTrace();
			return null;
		}
	}
	
	@Transactional
	public List<Blog> getBlogByUser(String username) 
	{
		log.info("Blog List by User started");
		try
		{
			String hql_string = "FROM Blog WHERE username = '"+username+"'";
			@SuppressWarnings("rawtypes")
			Query query = sessionFactory.getCurrentSession().createQuery(hql_string);
			@SuppressWarnings("unchecked")
			List<Blog> list = query.list();
			if (list != null && !list.isEmpty()) 
			{
				log.info("Blog List Retrieved");
				return list;
			}
			log.info("Blog List Mostly Empty");
			return null;
		} 
		catch(Exception ex)
		{
			log.info("Error Getting Blog List");
			ex.printStackTrace();
			return null;
		}
	}
	
	@Transactional
	public List<Blog> getAllBlogs() 
	{
		log.info("Get All Blog List Started");
		try
		{
			String hql_string = "FROM Blog";
			@SuppressWarnings("rawtypes")
			Query query = sessionFactory.getCurrentSession().createQuery(hql_string);
			@SuppressWarnings("unchecked")
			List<Blog> list = query.list();
			if (list != null && !list.isEmpty()) 
			{
				log.info("Blog List Retrieved");
				return list;
			}
			log.info("Blog List must be empty.");
			return null;
		} 
		catch(Exception ex)
		{
			log.info("Error Getting Blog List");
			ex.printStackTrace();
			return null;
		}
	}

	@Transactional
	public List<Blog> getApprovedBlogs() 
	{
		log.info("Approved Blogs List");
		try
		{
			String hql_string = "FROM Blog WHERE status = 'Approved'";
			@SuppressWarnings("rawtypes")
			Query query = sessionFactory.getCurrentSession().createQuery(hql_string);
			@SuppressWarnings("unchecked")
			List<Blog> list = query.list();
			if (list != null && !list.isEmpty()) 
			{
				log.info("Blog List Retrieved");
				return list;
			}
			else
			{
				log.info("Blog List maybe Empty");
				return null;
			}
		} 
		catch(Exception ex)
		{
			log.info("Error Getting Blog List");
			ex.printStackTrace();
			return null;
		}
	}	
}