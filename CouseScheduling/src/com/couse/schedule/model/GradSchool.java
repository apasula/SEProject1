package com.couse.schedule.model;

public class GradSchool {

	private String name;
	private String abbrevation;

	public GradSchool() {
		super();
	}

	public GradSchool(String name, String abbrevation) {
		super();
		this.name = name;
		this.abbrevation = abbrevation;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAbbrevation() {
		return abbrevation;
	}

	public void setAbbrevation(String abbrevation) {
		this.abbrevation = abbrevation;
	}

}
