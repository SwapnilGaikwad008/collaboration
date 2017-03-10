package com.niit.collaboration.controllers;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.niit.collaboration.dao.EventDAO;
import com.niit.collaboration.model.Event;

@RestController
public class EventController 
{
	Logger log = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	private EventDAO eventDAO;
	
	@Autowired
	private Event event;
	
	@PostMapping("/addEvent")
	public ResponseEntity<Event>addEvent(@RequestBody Event event){
		eventDAO.save(event);
		event.setErrorCode("200");
		event.setErrorMsg("SUCCESS");
		return new ResponseEntity<Event>(event, HttpStatus.OK);
	}
	
	@GetMapping("/listEvent")
	public ResponseEntity<List<Event>> getList(){
		List<Event> list = eventDAO.list();
		DateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
		for (Event e : list) {
			String d = df.format(e.getDate_time());
			e.setDate4(d);
		}
		event.setErrorCode("200");
		event.setErrorMsg("Success.....");
		return new ResponseEntity<List<Event>>(list, HttpStatus.OK);
	}
	
	@GetMapping("/delete_event-{id}")
	public ResponseEntity<Event> deleteEvent(@PathVariable("id") String id){
		eventDAO.delete(id);
		event.setErrorCode("200");
		event.setErrorMsg("Deleted Successfully");
		return new ResponseEntity<Event>(event, HttpStatus.OK);
	}
	
	
	@PutMapping("/updateEvent")
	public ResponseEntity<Event>editEvent(@RequestBody Event event)
	{
		eventDAO.update(event);
		event.setErrorCode("200");
		event.setErrorMsg("Edited SuccessFully");
		return new ResponseEntity<Event>(event, HttpStatus.OK);
	}
	
	
	
	
	
}
