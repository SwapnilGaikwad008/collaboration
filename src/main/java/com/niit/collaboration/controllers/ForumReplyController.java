package com.niit.collaboration.controllers;



import java.util.List;

import javax.servlet.http.HttpSession;

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

import com.niit.collaboration.dao.ForumReplyDAO;
import com.niit.collaboration.model.ForumReply;
import com.niit.collaboration.util.Date_Time;

@RestController
public class ForumReplyController 
{

	private static final Logger log = LoggerFactory.getLogger(ForumReplyController.class);
	
	@Autowired
	private ForumReply forumReply;
	
	@Autowired
	private ForumReplyDAO forumReplyDAO;
	
	@Autowired
	private HttpSession session;
	
	@PostMapping("/addReply")
	public ResponseEntity<ForumReply> addReply(@RequestBody ForumReply forumReply)
	{
		log.info("Adding Forum Reply");
		forumReply.setUsername(session.getAttribute("username").toString());
		Date_Time dt = new Date_Time();
		forumReply.setPostedAt(dt.getDateTime());
		forumReply.setRating(0);
		boolean value = forumReplyDAO.addReply(forumReply);
		if(value)
		{
			forumReply.setErrorCode("200");
			forumReply.setErrorMsg("Forum Reply Added");
			log.info("Reply successfully added");
		}
		else
		{
			forumReply.setErrorCode("200");
			forumReply.setErrorMsg("Reply not added");
			log.info("Reply not added");	
			return null;
		}
		return new ResponseEntity<ForumReply>(forumReply, HttpStatus.OK);
	}
	
	@GetMapping("/deleteReply-{id}")
	public ResponseEntity<ForumReply> deleteReply(@PathVariable ("id") int id)
	{
		log.info("Entering Delete Reply");
		try
		{
			forumReply = forumReplyDAO.getReply(id);
		}	catch(Exception ex)
		{
			log.error("Error getting reply");
			ex.printStackTrace();
			return null;
		}
		boolean value = forumReplyDAO.deleteReply(forumReply);
		if(value)
		{
			forumReply.setErrorCode("200");
			forumReply.setErrorMsg("Forum has been deleted");
		}
		else
		{
			log.error("Delete reply failed");
			return null;
		}
		return new ResponseEntity<ForumReply>(forumReply, HttpStatus.OK);
	}
	
	@GetMapping("/getForumReplies-{id}")
	public ResponseEntity<List<ForumReply>> listReplies(@PathVariable ("id") int forum_id)
	{
		log.info("Entering Get Replies for forum -"+forum_id);
		List<ForumReply> list = forumReplyDAO.getForumReply(forum_id);
		if(list.isEmpty() || list==null)
		{
			log.error("No replies found...");
			return null;
		}
		else
		{
			log.info("Replies have been retrieved");
			return new ResponseEntity<List<ForumReply>>(list, HttpStatus.OK);
		}
	}
}