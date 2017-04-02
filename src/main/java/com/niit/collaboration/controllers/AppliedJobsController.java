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

import com.niit.collaboration.dao.JobAppliedDAO;
import com.niit.collaboration.model.Job;
import com.niit.collaboration.model.JobApplied;
import com.niit.collaboration.util.Date_Time;

@RestController
public class AppliedJobsController 
{
	Logger log = LoggerFactory.getLogger(AppliedJobsController.class);
	
	@Autowired
	private JobApplied jobApplied;
	
	@Autowired
	private HttpSession session;
	
	@Autowired
	private JobAppliedDAO jobAppliedDAO;
	
	@PostMapping("/applyJob")
	public ResponseEntity<JobApplied> applyJob(@RequestBody Job job)
	{
		log.info("Apply Job initiated");
		
		jobApplied.setCompany(job.getCompany());
		Date_Time dt = new Date_Time();
		jobApplied.setDate(dt.getDateTime());
		jobApplied.setLocation(job.getLocation());
		jobApplied.setPosition(job.getPosition());
		jobApplied.setStatus('A');
		jobApplied.setTitle(job.getTitle());
		jobApplied.setUsername(session.getAttribute("username").toString());

		boolean value = jobAppliedDAO.applyNew(jobApplied);
		if(value)
		{
			log.info("Job has been Applied for");
			jobApplied.setErrorCode("200");
			jobApplied.setErrorMsg("Job has been Applied");
		}
		else
		{
			log.info("Apply job has got some error");
			jobApplied = new JobApplied();
			jobApplied.setErrorCode("400");
			jobApplied.setErrorMsg("Job has not been Added");
		}
		return new ResponseEntity<JobApplied> (jobApplied, HttpStatus.OK);
	}

	@GetMapping("/viewMyJobs")
	public ResponseEntity<List<JobApplied>> getJobsofUser()
	{
		log.info("Fetching Jobs by username");
		if(session.getAttribute("username")==null)
		{
			return null;
		}
		
		List<JobApplied> list = jobAppliedDAO.listByUser(session.getAttribute("username").toString());
		if(list.isEmpty())
		{
			log.info("Job list seems to be empty");
			return null;
		}
		else
		{
			log.info("Job list has been found");
			for(JobApplied jobApplied : list)
			{
				jobApplied.setErrorCode("200");
				jobApplied.setErrorMsg("You have applied for this Job");
			}
		}
		return new ResponseEntity<List<JobApplied>> (list, HttpStatus.OK);
	}
	
	@GetMapping("/viewApplications")
	public ResponseEntity<List<JobApplied>> getJobsByCompany()
	{
		log.info("Fetching Jobs by username");
		List<JobApplied> list = jobAppliedDAO.listByCompany();
		if(list.isEmpty())
		{
			log.info("Job list seems to be empty");
			return null;
		}
		else
		{
			log.info("Job list has been found");
			for(JobApplied jobApplied : list)
			{
				jobApplied.setErrorCode("200");
				jobApplied.setErrorMsg("Has applied for this Job");
			}
		}
		return new ResponseEntity<List<JobApplied>> (list, HttpStatus.OK);
	}
}