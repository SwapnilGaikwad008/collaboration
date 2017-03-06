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

import com.niit.collaboration.dao.JobDAO;
import com.niit.collaboration.model.Job;
import com.niit.collaboration.util.Date_Time;

@RestController
public class JobController 
{
	Logger log = LoggerFactory.getLogger(JobController.class);
	
	@Autowired
	private JobDAO jobDAO;
	
	@Autowired
	private Job job;
	
	@Autowired 
	HttpSession session;
	
	@PostMapping("/addJob")
	public ResponseEntity<Job> addJob(@RequestBody Job job)
	{
		job.setStatus('Y');
		Date_Time dt = new Date_Time();
		job.setDate(dt.getDateTime());
		
		boolean value = jobDAO.addJob(job);
		if(value == true)
		{
			job.setErrorCode("200");
			job.setErrorMsg("Job added Successfully");
		}
		else
		{
			job.setErrorCode("404");
			job.setErrorMsg("Job has not been added");
		}
		return new ResponseEntity<Job>(job, HttpStatus.OK);
	}
	
	@PostMapping("/getJob")
	public ResponseEntity<Job> getJob(@RequestBody Job job)
	{
		job = jobDAO.getJob(job.getTitle());
		job.setErrorCode("200");
		job.setErrorMsg("Job has been retrieved");
		return new ResponseEntity<Job>(job, HttpStatus.OK);
	}
	
	@GetMapping("/getAllJobs")
	public ResponseEntity<List<Job>> getJobList()
	{
		List<Job> jobs = jobDAO.listJobs();
		job.setErrorCode("200");
		job.setErrorMsg("Job has been retrieved");
		return new ResponseEntity<List<Job>>(jobs, HttpStatus.OK);
	}
	
	@GetMapping("/deleteJob-{id}")
	public ResponseEntity<Job> deleteJob(@PathVariable ("id") int id)
	{
		boolean value = jobDAO.deleteJob(id);
		if(value==true)
		{
			job.setErrorCode("200");
			job.setErrorMsg("Job has been deleted");
		}
		else
		{
			job = new Job();
			job.setErrorCode("404");
			job.setErrorMsg("Job has not been deleted");
		}
		return new ResponseEntity<Job>(job, HttpStatus.OK);
	}
	
	@GetMapping("/invalidate-{id}")
	public ResponseEntity<Job> invalidateJob(@PathVariable ("id") int id)
	{
		boolean value = jobDAO.invalidateJob(id);
		if(value==true)
		{
			job.setErrorCode("200");
			job.setErrorMsg("Job has been Invalidated");
		}
		else
		{
			job = new Job();
			job.setErrorCode("404");
			job.setErrorMsg("Job has been Validated");
		}
		return new ResponseEntity<Job>(job, HttpStatus.OK);
	}
}