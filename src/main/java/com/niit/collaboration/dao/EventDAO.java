package com.niit.collaboration.dao;


import java.util.List;

import com.niit.collaboration.model.Event;

public interface EventDAO
{
	public boolean addEvent(Event event);
	
	public boolean deleteEvent(int id);
	
	public Event getEvent(int id);
	
	public List<Event> listEvent();
	
}