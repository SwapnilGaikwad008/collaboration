package com.niit.collaboration.DAO;

import java.util.List;

import com.niit.collaboration.model.Job;

public interface JobDAO {
     
	public boolean SaveJob(Job job);
	
	public boolean UpdateJob(Job job);
	
	public boolean DeleteJob(Job job);
	
	public Job getByid(int id);
	
	public List<Job> getJobs(String username);
	
	public List<Job> getOpenJobs();
	
}
