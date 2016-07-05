package com.couse.schedule.model;

public class Faculty {

	private String id;
	private String fname;
	private String lname;
	private String degree;
	private String title;
	private String daystoTeach;
	private String coursespersemister;
	private String courseid;
	private int required;

	public Faculty() {
		super();
	}
	
	

	public String getId() {
		return id;
	}



	public void setId(String id) {
		this.id = id;
	}



	public String getCourseid() {
		return courseid;
	}

	public void setCourseid(String courseid) {
		this.courseid = courseid;
	}

	public int getRequired() {
		return required;
	}

	public void setRequired(int required) {
		this.required = required;
	}

	public String getCoursespersemister() {
		return coursespersemister;
	}

	public void setCoursespersemister(String coursespersemister) {
		this.coursespersemister = coursespersemister;
	}

	public String getFname() {
		return fname;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}

	public String getLname() {
		return lname;
	}

	public void setLname(String lname) {
		this.lname = lname;
	}

	public String getDegree() {
		return degree;
	}

	public void setDegree(String degree) {
		this.degree = degree;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDaystoTeach() {
		return daystoTeach;
	}

	public void setDaystoTeach(String daystoTeach) {
		this.daystoTeach = daystoTeach;
	}

	public void canTeacherAddSection(Schedule schedule) {
	}

}
