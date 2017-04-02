package com.niit.collaboration.model;



import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

@Entity
@Table(name="C_Friend")
@Component
public class Friend extends BaseDomain
{
	@Id
	private int id;
	
	@Column(nullable=false)
	private String userID;
	
	@Column(nullable=false)
	private String friendID;
	
	@Column(nullable=false)
	private String userFName;
	
	private String userLName;
	
	@Column(nullable=false)
	private char userIsOnline;
	
	@Column(nullable=false)
	private String friendFName;
	
	private String friendLName;
	
	@Column(nullable=false)
	private char friendisOnline;

	public String getUserFName() {
		return userFName;
	}
	public void setUserFName(String userFName) {
		this.userFName = userFName;
	}
	public String getUserLName() {
		return userLName;
	}
	public void setUserLName(String userLName) {
		this.userLName = userLName;
	}
	public char getUserIsOnline() {
		return userIsOnline;
	}
	public void setUserIsOnline(char userIsOnline) {
		this.userIsOnline = userIsOnline;
	}
	public String getFriendFName() {
		return friendFName;
	}
	public void setFriendFName(String friendFName) {
		this.friendFName = friendFName;
	}
	public String getFriendLName() {
		return friendLName;
	}
	public void setFriendLName(String friendLName) {
		this.friendLName = friendLName;
	}
	public char getFriendisOnline() {
		return friendisOnline;
	}
	public void setFriendisOnline(char friendisOnline) {
		this.friendisOnline = friendisOnline;
	}
	@Column(name="status", nullable=false)
	private char status;

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUserID() {
		return userID;
	}
	public void setUserID(String userID) {
		this.userID = userID;
	}
	public String getFriendID() {
		return friendID;
	}
	public void setFriendID(String friendID) {
		this.friendID = friendID;
	}
	public char getStatus() {
		return status;
	}
	public void setStatus(char status) {
		this.status = status;
	}
}