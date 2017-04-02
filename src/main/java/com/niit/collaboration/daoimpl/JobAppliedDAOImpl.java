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

import com.niit.collaboration.dao.JobAppliedDAO;
import com.niit.collaboration.model.JobApplied;

@Repository("jobAppliedDAO")
@EnableTransactionManagement
public class JobAppliedDAOImpl implements JobAppliedDAO
{
	private static final Logger log = LoggerFactory.getLogger(JobAppliedDAOImpl.class);
	
	@Autowired
	private SessionFactory sessionFactory;
	
	public JobAppliedDAOImpl(SessionFactory sessionFactory) 
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
	public boolean applyNew(JobApplied jobApplied) 
	{
			log.info("Job Applied started");
			try
			{
				sessionFactory.getCurrentSession().save(jobApplied);
				log.info("Apply Job Success");
				return true;
			}
			catch(Exception ex)
			{
				log.error("Apply job not successful");
				ex.printStackTrace();
				return false;
			}
	}

	@Transactional
	public List<JobApplied> listByUser(String username) 
	{
		log.info("Entering Job Applied by USER");
		try
		{
			String sql = "From JobApplied where username = '"+username+"'";
			Query query = sessionFactory.getCurrentSession().createQuery(sql);
			log.info("List Retrieved");
			return query.list();
		} 
		catch(Exception ex)
		{
			log.error("Error Getting List");
			ex.printStackTrace();
			return null;
		}
	}

	@Transactional
	public List<JobApplied> listByCompany()
	{
		log.info("Entering Job Applied by Title");
		try
		{
			String sql = "From JobApplied";
			Query query = sessionFactory.getCurrentSession().createQuery(sql);
			log.info("List Retrieved");
			return query.list();
		}
		catch(Exception ex)
		{
			log.error("Error Getting List");
			ex.printStackTrace();
			return null;
		}
	}
}