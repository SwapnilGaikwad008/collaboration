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

import com.niit.collaboration.dao.EventDAO;
import com.niit.collaboration.model.Event;

@SuppressWarnings("deprecation")
@Repository("eventDAO")
@EnableTransactionManagement
public class EventDAOImpl implements EventDAO
{
	Logger log = LoggerFactory.getLogger(JobAppliedDAOImpl.class);
	
	@Autowired
	private SessionFactory sessionFactory;
	
	public EventDAOImpl(SessionFactory sessionFactory) 
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
	public boolean addEvent(Event event) 
	{
		try
		{
			sessionFactory.getCurrentSession().save(event);
			log.info("Event has been saved");
			return true;
		} 
		catch(Exception ex)
		{
			ex.printStackTrace();
			log.error("Error saving Event");
			return false;
		}
	}

	@SuppressWarnings({ "rawtypes" })
	@Transactional
	public boolean deleteEvent(int id) 
	{
		log.info("Entering Delete Event");
		try 
		{
			String sql = "FROM Event where id = '"+id+"'";
			Query query = sessionFactory.getCurrentSession().createQuery(sql);
			Event event = (Event) query.uniqueResult();
			if(event == null)
			{
				log.info("Event Not Found");
				return false;
			}
			
			sessionFactory.getCurrentSession().delete(event);
			log.info("Success delete Event");
			return true;
		}
		catch (HibernateException e) 
		{
			log.error("Error Deleting Event");
			e.printStackTrace();
			return false;
		}
	}

	@Transactional
	public Event getEvent(int id)
	{
		try
		{
			String sql = "FROM Event where id = '"+id+"'";
			@SuppressWarnings("rawtypes")
			Query query = sessionFactory.getCurrentSession().createQuery(sql);
			Event event = (Event) query.uniqueResult();
			return event;
		}
		catch(HibernateException ex)
		{
			ex.printStackTrace();
			return null;	
		}
	}

	@Transactional
	public List<Event> listEvent() 
	{
		try
		{
			String sql = "FROM Event";
			@SuppressWarnings("rawtypes")
			Query query = sessionFactory.getCurrentSession().createQuery(sql);
			@SuppressWarnings("unchecked")
			List<Event> events = query.list();
			return events;
		}
		catch(HibernateException ex)
		{
			ex.printStackTrace();
			return null;	
		}
	}

}