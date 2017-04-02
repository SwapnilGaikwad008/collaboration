package com.niit.collaboration.controllers;


import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.niit.collaboration.dao.EventDAO;
import com.niit.collaboration.model.Event;
import com.niit.collaboration.util.Date_Time;

@RestController
public class EventController 
{
	Logger log = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	private EventDAO eventDAO;
	
	@Autowired
	private Event event;
	
	@GetMapping("/getEvents")
	public ResponseEntity<List<Event>> listEvent()
	{
		log.info("Entering List Event");
		List<Event> list = eventDAO.listEvent();
		if(list.isEmpty())
		{
			event.setErrorCode("400");
			event.setErrorMsg("List seems to be empty");
			list.add(event);
			return new ResponseEntity<List<Event>> (list,HttpStatus.OK);
		}
		else
		{
			Date_Time dt = new Date_Time();
			for(int i=0; i< list.size(); i++)
			{
				Date date = list.get(i).getDate();
				String eventDate = dt.toStringDate(date); 
				list.get(i).setEventDate(eventDate);
			}
			event.setErrorCode("200");
			event.setErrorMsg("List Retrieved");
			return new ResponseEntity<List<Event>> (list,HttpStatus.OK);
		}
	}
	
	@PostMapping("/addEvent")
	public ResponseEntity<Event> addEvent(@RequestBody Event event)
	{
		log.info("Entering Event add");
		Date_Time dt = new Date_Time();
		event.setPostedTime(dt.getDateTime());
		boolean value = eventDAO.addEvent(event);
		if(value)
		{
			event.setErrorCode("200");
			event.setErrorMsg("Event has been added");
		}
		else
		{
			event = new Event();
			event.setErrorCode("400");
			event.setErrorMsg("Event has not been added");
		}
		return new ResponseEntity<Event> (event, HttpStatus.OK);
	}
	
	@GetMapping("/deleteEvent-{id}")
	public ResponseEntity<Event> delete(@PathVariable ("id") int id)
	{
		log.info("Entering Event delete");
		boolean value = eventDAO.deleteEvent(id);
		if(value)
		{
			event.setErrorCode("200");
			event.setErrorMsg("Event has been deleted");
		}
		else
		{
			event = new Event();
			event.setErrorCode("400");
			event.setErrorMsg("Event has not been deleted");
		}
		return new ResponseEntity<Event> (event, HttpStatus.OK);	
	}
	
	@GetMapping("/getEvent-{id}")
	public ResponseEntity<Event> getEvent(@PathVariable ("id") int id)
	{
		log.info("Entering Get Event");
		event = eventDAO.getEvent(id);
		if(event == null)
		{
			event = new Event();
			event.setErrorCode("400");
			event.setErrorMsg("Event is not found");		
		}
		else
		{
			event.setErrorCode("200");
			event.setErrorMsg("Event found");
		}
		return new ResponseEntity<Event> (event, HttpStatus.OK);
	}
}