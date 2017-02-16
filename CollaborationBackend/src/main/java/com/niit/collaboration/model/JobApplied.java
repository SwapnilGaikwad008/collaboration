package com.niit.collaboration.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

@Component
@Entity
@Table(name = "C_JOB_APPLIED")
public class JobApplied {

	public int id;
	
	public String username;
	
	public Date date_applied;
	
	public String status;
	
	public String remarks;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Date getDate_applied() {
		return date_applied;
	}

	public void setDate_applied(Date date_applied) {
		this.date_applied = date_applied;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	
}
