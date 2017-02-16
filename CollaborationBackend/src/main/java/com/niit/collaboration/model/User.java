package com.niit.collaboration.model;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

@Component
@Entity
@Table(name= "C_USER_DETAIL")
public class User {
	
	public String id;
	
    public String name;
	
	public String password;
	
	public String Address;
	
	public String email;
	
	public String mobile;
	
	public String status;
	
	public String reason;
	
	public String role;
	
	public String is_online;
	
	public String is_offline;
	
	
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getAddress() {
		return Address;
	}

	public void setAddress(String address) {
		Address = address;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getIs_online() {
		return is_online;
	}

	public void setIs_online(String is_online) {
		this.is_online = is_online;
	}

	public String getIs_offline() {
		return is_offline;
	}

	public void setIs_offline(String is_offline) {
		this.is_offline = is_offline;
	}

}
