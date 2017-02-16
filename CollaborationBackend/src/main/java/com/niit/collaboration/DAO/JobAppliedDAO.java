package com.niit.collaboration.DAO;

import java.util.List;

import com.niit.collaboration.model.JobApplied;

public interface JobAppliedDAO {
	
	public JobApplied getJobApplication(String id);
	
    public List<JobApplied> list();
    
    public boolean SaveJobApplication(JobApplied jobApplied);
    
    public boolean DeleteJobApplied(JobApplied jobApplied);
    
    public boolean UpdateJobApplied(JobApplied jobApplied);
    
    public List<JobApplied> list(String emailId);
}
