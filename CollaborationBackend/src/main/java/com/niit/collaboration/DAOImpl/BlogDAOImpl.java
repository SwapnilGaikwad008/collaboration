package com.niit.collaboration.DAOImpl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.niit.collaboration.DAO.BlogDAO;
import com.niit.collaboration.model.Blog;

public class BlogDAOImpl implements BlogDAO {
	
	@Autowired
	public SessionFactory sessionFactory;
	
	public BlogDAOImpl(SessionFactory sessionFactory){
		try{ 
			this.sessionFactory = sessionFactory;
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}
   @Transactional
	public boolean addBlog(Blog blog) {
		try{
			blog.setStatus("Submitted");
			sessionFactory.getCurrentSession().saveOrUpdate(blog);
			return true;
		}catch(Exception e){
			return false;
		}
	}
   @Transactional
	public boolean approveBlog(Blog blog) {
       Blog saveBlog = blog;
       {
    	   String title = saveBlog.getBlog_title();
    	   String hql_string = "FROM Blog WHERE blog_title = '"+title+"'";
          Query query = sessionFactory.getCurrentSession().createQuery(hql_string);
          blog = (Blog) query.uniqueResult();
          sessionFactory.getCurrentSession().delete(blog);
       }try{
    	   sessionFactory.getCurrentSession().save(saveBlog);
    	   return true;
       }catch(Exception e){
    	   e.printStackTrace();
		return false;
       }
	}
    
   @Transactional
	public boolean updateBlog(Blog blog) {
		Blog saveBlog = blog;
		{
			String title = saveBlog.getBlog_title();
			String hql_string = "FROM Blog WHERE blog_title = '"+title+"'"; 
			 Query query = sessionFactory.getCurrentSession().createQuery(hql_string);
	          blog = (Blog) query.uniqueResult();
	          sessionFactory.getCurrentSession().delete(blog);
	       }try{
	    	   saveBlog.setStatus("Updated");
	    	   sessionFactory.getCurrentSession().save(saveBlog);
	    	   return true;
	       }catch(Exception e){
	    	   e.printStackTrace();
			return false;
	       }
	}
    
   @Transactional
	public boolean deleteBlog(Blog blog) {
		try{
			sessionFactory.getCurrentSession().delete(blog);
			return true;
		}catch(Exception e){
			return false;
		}
	}

   @Transactional
	public Blog getBlog(String title) {
		try{
			String hql_string = "FROM Blog WHERE blog_title = '"+title+"'";
			Query query = sessionFactory.getCurrentSession().createQuery(hql_string);
			return (Blog)query.uniqueResult();
		}catch(Exception e){
			e.printStackTrace();
		return null;
	}
   }		

	public List<Blog> getBlogByUser(String email) {
		try{
			String hql_string ="FROM Blog WHERE email = '"+email+"'";
			Query query = sessionFactory.getCurrentSession().createQuery(hql_string);
			List<Blog> list = query.list();
			if(list != null && !list.isEmpty())
			{
				return list;
			}
		return null;
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
    @Transactional
	public List<Blog> getApprovedBlogs() 
    {
		try{
			String hql_string = "FROM Blog WHERE status = 'Approved'";
			Query query = sessionFactory.getCurrentSession().createQuery(hql_string);
			List<Blog> list = query.list();
			if(list != null && !list.isEmpty())
			{
				return list;
			}else
			{
				return null;
			}
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
    }

		
	

	@Transactional
	public List<Blog> getAllBlogs() 
	{
		try
		{
			String hql_string = "FROM Blog";
			
			Query query = sessionFactory.getCurrentSession().createQuery(hql_string);
			
			List<Blog> list = query.list();
			if (list != null && !list.isEmpty()) 
			{
				return list;
			}
			return null;
		} 
		catch(Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}

}
