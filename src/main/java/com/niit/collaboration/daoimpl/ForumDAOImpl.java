package com.niit.collaboration.daoimpl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import com.niit.collaboration.dao.ForumDAO;
import com.niit.collaboration.model.Forum;

@SuppressWarnings("deprecation")
@Repository("forumDAO")
@EnableTransactionManagement
public class ForumDAOImpl implements ForumDAO
{
	private static final Logger log = LoggerFactory.getLogger(ForumDAOImpl.class);
	
	@Autowired
	private SessionFactory sessionFactory;
	
	public ForumDAOImpl(SessionFactory sessionFactory) 
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
	
	private Integer getMaxId()
	{
		log.info("->->Starting of the method getMaxId");

		String hql = "select max(id) from Forum";
		@SuppressWarnings("rawtypes")
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		Integer maxID;
		try 
		{
			maxID = (Integer) query.uniqueResult();
			if(maxID==null)
			{
				maxID = 10000;
			}
		} catch (Exception e) 
		{
			log.error("Error getting Max ID");
			e.printStackTrace();
			return 10;
		}
		log.info("Max id :" + maxID);
		return maxID;
	}
	
	@Transactional
	public boolean addForum(Forum forum) 
	{
		log.info("Entering add forum");
		try
		{
			forum.setId(getMaxId()+1);
			forum.setStatus('P');
			sessionFactory.getCurrentSession().save(forum);
			log.info("Forum has been added "+forum.getForum_id());
			return true;
		} catch (Exception ex)
		{
			log.error("Forum has not been saved");
			ex.printStackTrace();
		}
			return false;
	}
	
	@Transactional
	public boolean updateForum(Forum forum)
	{
		log.info("Entering Update Forum");
		try
		{
			sessionFactory.getCurrentSession().update(forum);
			log.info("Forum has been updated "+forum.getForum_id());
			return true;
		} catch (Exception ex)
		{
			log.error("Forum has not been updated");
			ex.printStackTrace();
		}
			return false;
	}

	@Transactional
	public boolean deleteForum(int id) 
	{
		log.info("Entering delete forum");
		try
		{
			Forum forum = getForum(id);
			sessionFactory.getCurrentSession().delete(forum);
			log.info("Forum has been deleted");
			return true;
		} catch (Exception ex)
		{
			log.error("Forum has not been deleted ");
			ex.printStackTrace();
		}
			return false;
	}

	@Transactional
	public Forum getForum(int id) 
	{
		log.debug("Starting of Method Get Forum");
		try
		{
			Forum forum =  sessionFactory.getCurrentSession().get(Forum.class, id);
			forum.setErrorCode("200");
			forum.setErrorMsg("Forum Found");
			log.info("Forum found");
			return forum;
		}
		catch(Exception ex)
		{
			log.error("Error getting forum");
			Forum forum = new Forum();
			ex.printStackTrace();
			forum.setErrorCode("404");
			forum.setErrorMsg("Forum Not Found");
			return forum;
		}
	}

	@SuppressWarnings({ "unchecked" })
	@Transactional
	public List<Forum> getForumList() 
	{
		log.info("Starting of List Forum method");
		try
		{
			String sql = "FROM Forum";
			@SuppressWarnings("rawtypes")
			Query query = sessionFactory.getCurrentSession().createQuery(sql);
			log.info("Forum list has been retrieved");
			return query.list();
		}	catch(Exception ex)
		{
			log.error("Error retrieving Forum List");
			ex.printStackTrace();
			return null;
		}
	}
	
	@SuppressWarnings({ "unchecked" })
	@Transactional
	public List<Forum> approvedForums() 
	{
		log.info("Starting of List Forum method");
		try
		{
			String sql = "FROM Forum where status = 'A'";
			@SuppressWarnings("rawtypes")
			Query query = sessionFactory.getCurrentSession().createQuery(sql);
			log.info("Forum list has been retrieved");
			return query.list();
		}	catch(Exception ex)
		{
			log.error("Error retrieving Forum List");
			ex.printStackTrace();
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	@Transactional
	public List<Forum> getUserForums(String username) 
	{
		log.info("Starting of List Forum by user");
		try
		{
			String sql = "FROM Forum where username = '"+username+"'";
			@SuppressWarnings("rawtypes")
			Query query = sessionFactory.getCurrentSession().createQuery(sql);
			log.info("Forum list has been retrieved");
			return query.list();
		}	catch(Exception ex)
		{
			log.error("Error retrieving Forum List");
			ex.printStackTrace();
			return null;
		}
	}
}