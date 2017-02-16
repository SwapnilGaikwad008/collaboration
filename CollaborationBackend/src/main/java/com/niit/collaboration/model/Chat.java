package com.niit.collaboration.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

@Component
@Entity
@Table(name="C_CHAT")
public class Chat {
	
	public int id;
    
	public String emailid;
	
	public String friendemailid;
	
	public String message;
	
	public Date date_time;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	 public String getEmailid() {
			return emailid;
		}

		public void setEmailid(String emailid) {
			this.emailid = emailid;
		}

		public String getFriendemailid() {
			return friendemailid;
		}

		public void setFriendemailid(String friendemailid) {
			this.friendemailid = friendemailid;
		}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Date getDate_time() {
		return date_time;
	}

	public void setDate_time(Date date_time) {
		this.date_time = date_time;
	}

	

}
