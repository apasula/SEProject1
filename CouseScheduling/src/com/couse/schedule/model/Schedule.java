package com.couse.schedule.model;

public class Schedule {

	private int id;
	private String term;
	private String status;
	private String sectionname;
	private String meetinginfo;
	private String faculty;
	private String available;
	private String credits;
	private String academiclevel;
	
    private boolean hasChanged = false;

	public boolean hasChanged() {
        return hasChanged;
    }


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTerm() {
		return term;
	}

	public void setTerm(String term) {
		this.term = term;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getSectionname() {
		return sectionname;
	}

	public void setSectionname(String sectionname) {
		this.sectionname = sectionname;
	}

	public String getMeetinginfo() {
		return meetinginfo;
	}

	public void setMeetinginfo(String meetinginfo) {
		this.meetinginfo = meetinginfo;
	}

	public String getFaculty() {
		return faculty;
	}

	public void setFaculty(String faculty) {
        hasChanged = true;
		this.faculty = faculty;
	}

	public String getAvailable() {
		return available;
	}

	public void setAvailable(String available) {
		this.available = available;
	}

	public String getCredits() {
		return credits;
	}

	public void setCredits(String credits) {
		this.credits = credits;
	}

	public String getAcademiclevel() {
		return academiclevel;
	}

	public void setAcademiclevel(String academiclevel) {
		this.academiclevel = academiclevel;
	}

}
