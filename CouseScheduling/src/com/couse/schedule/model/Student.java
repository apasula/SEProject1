package com.couse.schedule.model;

public class Student {

	private String id;
	private String studentid;
	private String name;
	private String year;

	public Student() {
		super();
	}

	public Student(String id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public String getStudentid() {
		return studentid;
	}

	public void setStudentid(String studentid) {
		this.studentid = studentid;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

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

}
