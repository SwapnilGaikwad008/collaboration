package com.niit.collaboration.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

@Entity
@Table(name = "C_JOB")
@Component
public class Job extends BaseDomain
{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int job_id;
	private String title;
	private String username;
	private char status;
	private String position;
	private String company;
	@Lob
	private String description;
	
	@Column(name="package")
	private String salary;
	
	private String location;
	private String q_10;
	private String q_12;
	private String q_ug;
	@Column(name="date_added")
	private String date;
	private int vacancy;
	
	public int getJob_id() {
		return job_id;
	}
	public void setJob_id(int job_id) {
		this.job_id = job_id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public char getStatus() {
		return status;
	}
	public void setStatus(char status) {
		this.status = status;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getSalary() {
		return salary;
	}
	public void setSalary(String salary) {
		this.salary = salary;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getQ_10() {
		return q_10;
	}
	public void setQ_10(String q_10) {
		this.q_10 = q_10;
	}
	public String getQ_12() {
		return q_12;
	}
	public void setQ_12(String q_12) {
		this.q_12 = q_12;
	}
	public String getQ_ug() {
		return q_ug;
	}
	public void setQ_ug(String q_ug) {
		this.q_ug = q_ug;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public int getVacancy() {
		return vacancy;
	}
	public void setVacancy(int vacancy) {
		this.vacancy = vacancy;
	}
}