package com.couse.schedule.model;

public class StudentCourse {

	private String grade;
	private String couseId;
	private String courseName;
	private String year;
	private String depid;
	private String attr;
	private String studentId;

	public StudentCourse() {
		super();
	}

	public StudentCourse(String grade) {
		super();
		this.grade = grade;
	}

	public String getStudentId() {
		return studentId;
	}

	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}

	public String getCouseId() {
		return couseId;
	}

	public void setCouseId(String couseId) {
		this.couseId = couseId;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getDepid() {
		return depid;
	}

	public void setDepid(String depid) {
		this.depid = depid;
	}

	public String getAttr() {
		return attr;
	}

	public void setAttr(String attr) {
		this.attr = attr;
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

}
