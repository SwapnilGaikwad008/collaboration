package com.niit.collaboration.dao;

import java.util.List;

import com.niit.collaboration.model.Event;

public interface EventDAO {


	public void save(Event event);
	
	public void update(Event event);
	
	public void delete(String id);
	
	public List<Event> list();
	
}