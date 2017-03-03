package com.niit.collaboration.daoimpl;


import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import com.niit.collaboration.dao.JobDAO;
import com.niit.collaboration.model.Job;

@SuppressWarnings("deprecation")
@Repository("jobDAO")
@EnableTransactionManagement
public class JobDAOImpl implements JobDAO
{
	private static final Logger log = LoggerFactory.getLogger(UserDAOImpl.class);
	
	@Autowired
	private SessionFactory sessionFactory;
	
	public JobDAOImpl(SessionFactory sessionFactory)
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
	public boolean addJob(Job job)
	{
		log.info("Job Add started");
		try
		{
			sessionFactory.getCurrentSession().save(job);
			log.info("Add Job Success");
			return true;
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
			return false;
		}
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Transactional
	public List<Job> listJobs()
	{
		log.info("List jobs method Started");
		try
		{
			String hql = "FROM Job";
			Query query = sessionFactory.getCurrentSession().createQuery(hql);
			log.info("List of Jobs retrieved");
			return query.list();
		}	
		catch(Exception ex)
		{
			log.info("Error occured");
			ex.printStackTrace();
			return null;
		}	
	}

	@Transactional
	public boolean deleteJob(int job_id) 
	{
		log.info("Delete Job Method Started");
		try {
			Job job =  sessionFactory.getCurrentSession().get(Job.class, job_id);
			sessionFactory.getCurrentSession().delete(job);
			log.info("Delete job Success");
			return true;
		} 
		catch (HibernateException e) 
		{
			log.info("Error Deleting Blog");
			e.printStackTrace();
			return false;
		}
	}

	@Transactional
	public boolean invalidateJob(int job_id)
	{
		log.info("Invalidate a Job Started");
		try
		{
			Job job =  sessionFactory.getCurrentSession().get(Job.class, job_id);
			Job saveJob = job;
			sessionFactory.getCurrentSession().delete(job);
			log.info("Processing Request");
			job.setStatus('N');
			sessionFactory.getCurrentSession().save(saveJob);
			log.info("Job as been invalidated");
			return true;
		}
		catch(Exception ex)
		{
			log.error("Error updating Job");
			ex.printStackTrace();
			return false;	
		}
		
	}

	@Transactional
	public Job getJob(String name) 
	{
		log.info("Entered Get Job");
		try
		{			
			String hql = "FROM Job where title = '"+name+"'";
			@SuppressWarnings("rawtypes")
			Query query = sessionFactory.getCurrentSession().createQuery(hql);
			log.info("List of Jobs retrieved");
			return (Job) query.uniqueResult();	
		}
		catch(Exception ex)
		{
			log.error("Error retreiving Job");
			ex.printStackTrace();
			return null;
		}
	}
}