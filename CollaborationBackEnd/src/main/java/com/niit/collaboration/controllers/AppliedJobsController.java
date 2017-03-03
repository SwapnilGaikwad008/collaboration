package com.niit.collaboration.controllers;


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

import com.niit.collaboration.dao.JobAppliedDAO;
import com.niit.collaboration.model.JobApplied;
import com.niit.collaboration.util.Date_Time;

@RestController
public class AppliedJobsController 
{
	Logger log = LoggerFactory.getLogger(AppliedJobsController.class);
	
	@Autowired
	private JobApplied jobApplied;
	
	@Autowired
	private JobAppliedDAO jobAppliedDAO;
	
	@PostMapping("/applyJob")
	public ResponseEntity<JobApplied> applyJob(@RequestBody JobApplied jobApplied)
	{
		log.info("Apply Job initiated");
		Date_Time dt = new Date_Time();
		jobApplied.setDate(dt.getDateTime());
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

	@GetMapping("/viewMyJobs-{username}")
	public ResponseEntity<List<JobApplied>> getJobsofUser(@PathVariable ("username") String username)
	{
		log.info("Fetching Jobs by username");
		List<JobApplied> list = jobAppliedDAO.listByUser(username);
		if(list.isEmpty())
		{
			log.info("Job list seems to be empty");
			jobApplied = new JobApplied();
			jobApplied.setErrorCode("400");
			jobApplied.setErrorMsg("Seems like you have not enrolled for any job");
			list.add(jobApplied);
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
	
	@GetMapping("/viewApplications-{title}")
	public ResponseEntity<List<JobApplied>> getJobsByCompany(@PathVariable ("title") String title)
	{
		log.info("Fetching Jobs by username");
		List<JobApplied> list = jobAppliedDAO.listByCompany(title);
		if(list.isEmpty())
		{
			log.info("Job list seems to be empty");
			jobApplied = new JobApplied();
			jobApplied.setErrorCode("400");
			jobApplied.setErrorMsg("Seems like no one has applied for any job");
			list.add(jobApplied);
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