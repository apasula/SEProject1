package com.couse.schedule.model;

public class Degree {

	private String code;
	private String name;
	private String department;
	private String track;
	private String id;

	public Degree() {
		super();
	}

	public Degree(String code, String name, String department, String track) {
		super();
		this.code = code;
		this.name = name;
		this.department = department;
		this.track = track;
	}
	
	

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getTrack() {
		return track;
	}

	public void setTrack(String track) {
		this.track = track;
	}

	public void numberCourseNeeded() {
	}

	public void listCourseToTake(Student student) {
	}
}
