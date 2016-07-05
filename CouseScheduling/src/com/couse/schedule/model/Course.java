package com.couse.schedule.model;

public class Course {

	private String id;

	private String coursename;
	private String couseid;
	private String coursedescription;
	private int deptid;
	
	

	public int getDeptid() {
		return deptid;
	}

	public void setDeptid(int deptid) {
		this.deptid = deptid;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCoursename() {
		return coursename;
	}

	public void setCoursename(String coursename) {
		this.coursename = coursename;
	}

	public String getCouseid() {
		return couseid;
	}

	public void setCouseid(String couseid) {
		this.couseid = couseid;
	}

	public String getCoursedescription() {
		return coursedescription;
	}

	public void setCoursedescription(String coursedescription) {
		this.coursedescription = coursedescription;
	}

}
