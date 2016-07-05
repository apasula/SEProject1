package com.couse.schedule.model;

public class DegreePlanReq {

	private String description;
	private String hours;
	private String type;

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getHours() {
		return hours;
	}

	public void setHours(String hours) {
		this.hours = hours;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public void listCoursesToTake(Student student) {
	}
}
