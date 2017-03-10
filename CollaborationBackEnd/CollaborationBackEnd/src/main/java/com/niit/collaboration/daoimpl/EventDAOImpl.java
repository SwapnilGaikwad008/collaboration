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
	public void save(Event event) {
		sessionFactory.getCurrentSession().saveOrUpdate(event);
	}
 
	@Transactional
	public void update(Event event) {
		sessionFactory.getCurrentSession().update(event);		
	}

	@Transactional
	public void delete(String id) {
       Event event = new Event();
       event.setId(id);
       sessionFactory.getCurrentSession().delete(event);
	}

	@Transactional
	public List<Event> list() {
		String hql = "from Event";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		return query.list();
	}


}