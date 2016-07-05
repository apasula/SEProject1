package com.couse.schedule.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import com.course.schedule.db.CourseDB;
import com.course.schedule.db.DepartmentDB;
import com.course.schedule.db.FacultyDB;
import com.course.schedule.db.SectionDB;
import com.course.schedule.db.StudentDB;
import com.couse.schedule.model.Course;
import com.couse.schedule.model.Degree;
import com.couse.schedule.model.Schedule;
import com.couse.schedule.model.StudentCourse;

public class ScheduleService {

	public static Date parseDate(String date) {
		try {
			return new SimpleDateFormat("MM-dd-YYY").parse(date);
		} catch (ParseException e) {
			return null;
		}
	}

	public void generateIndividualSchedule(String sem, int year) {

		String timing1 = " 2.30 PM to 6.15 PM";
		String timing2 = " 6 PM to 9.30 PM";

		Date springstartdate = parseDate("01-01-2015");
		Date summerstartdate = parseDate("05-01-2015");
		Date fallstartdate = parseDate("09-01-2015");

		ArrayList<String> sections = new ArrayList<>();
		sections.add("A");
		sections.add("B");
		sections.add("C");
		sections.add("D");
		sections.add("E");
		sections.add("F");

		ArrayList<Schedule> schedules = new ArrayList<>();

		DepartmentDB departmentDB = new DepartmentDB();
		StudentDB studentDB = new StudentDB();
		FacultyDB facultyDB = new FacultyDB();
		CourseDB courseDB = new CourseDB();
		SectionDB sectionDB = new SectionDB();

		String semname ="";
		if (sem.equals("SP")) {
			semname = "SPRING";
		} else if (sem.equals("SU")) {
			semname = "SUMMER";
		} else if (sem.equals("FA")) {
			semname = "FALL";
		}
		
		sectionDB.deleteSchedule(semname+" "+year);
		String[] sems = new String[3];
		sems[0] = "SP";
		sems[1] = "SU";
		sems[2] = "FA";

		// int year = 2015;

		int timetrackor = 0;

		ArrayList<Degree> degrees = departmentDB.getDepartments();
		ArrayList<String> processedDegreeCodes = new ArrayList<>();

		ArrayList<String> donecourses = new ArrayList<>();
		for (Degree degree : degrees) {

			String code = degree.getCode().replaceAll(".", "");

			ArrayList<String> courseids = departmentDB.getCoursesbyId(degree
					.getId());

			for (String courseid : courseids) {
				System.out.println(courseid);

				if (!donecourses.contains(courseid)) {
					donecourses.add(courseid);
				} else {
					continue;
				}
				// year = 2015;
				// for (int k = 0; k < 2; k++) {
				// for (int i = 0; i < sems.length; i++) {

				ArrayList<StudentCourse> studentcourses = studentDB
						.getStudentsbyCourse(year + sem, courseid);

				ArrayList<String> instructors = facultyDB
						.getFacultybyCourseId(courseid);

				if(instructors.size()==0)
				{
					instructors.add("To be decided");
				}
				int studentssize = studentcourses.size();
				if (studentssize == 0)
					continue;

				int section = (studentssize / 25) + 1;

				if (studentssize % 25 == 0) {
					section = studentssize / 25;
				}
				int availablestudents = studentssize;

				System.out.println(studentssize + " " + section);

				int professortrackor = 0;
				for (int sectionindex = 0; sectionindex < section; sectionindex++) {
					
					
					if (professortrackor > instructors.size()-1) {

						professortrackor =0;
						
					}
						Schedule schedule = new Schedule();
						if (sem.equals("SP")) {
							schedule.setTerm("SPRING " + year);
						} else if (sem.equals("SU")) {
							schedule.setTerm("SUMMER " + year);
						} else if (sem.equals("FA")) {
							schedule.setTerm("FALL " + year);
						} else
							schedule.setTerm("FALL " + year);

						schedule.setStatus("Open");
						Course coursedetails = new Course();

						String sectionname = "";
						if (section > 1) {
							sectionname = " ( " + sections.get(sectionindex)
									+ " ) ";
						}

						coursedetails = courseDB.getCourse(courseid);
						if (coursedetails == null)
							schedule.setSectionname(courseid + sectionname
									+ " ");
						else
							schedule.setSectionname(courseid + sectionname
									+ " " + coursedetails.getCoursename());

						schedule.setMeetinginfo("");
						schedule.setFaculty(instructors.get(professortrackor));
					
						if(availablestudents-25>0){
							schedule.setAvailable("25 / 25/ 0");
							availablestudents = availablestudents-25;
						}
						else{
							
							schedule.setAvailable(availablestudents+" / 25/ 0");
							
						}

						schedule.setCredits("3.00");
						schedule.setAcademiclevel("Graduate");

						springstartdate.setYear(year);
						String meetinginfo = "Lecture ";

						Calendar c = Calendar.getInstance();

						if (sem.equals("SP")) {

							springstartdate.setYear(year);
							c.setTime(springstartdate);
							int dayOfWeek = c.get(Calendar.DAY_OF_WEEK);
							if (dayOfWeek == Calendar.FRIDAY) {
								c.add(Calendar.DATE, 3);
							} else if (dayOfWeek == Calendar.SATURDAY) {
								c.add(Calendar.DATE, 2);
							} else {
								c.add(Calendar.DATE, 1);
							}

							springstartdate = c.getTime();

						} else if (sem.equals("SU")) {

							summerstartdate.setYear(year);
							c.setTime(summerstartdate);
							int dayOfWeek = c.get(Calendar.DAY_OF_WEEK);
							if (dayOfWeek == Calendar.FRIDAY) {
								c.add(Calendar.DATE, 3);
							} else if (dayOfWeek == Calendar.SATURDAY) {
								c.add(Calendar.DATE, 2);
							} else {
								c.add(Calendar.DATE, 1);
							}

							summerstartdate = c.getTime();
						} else {

							fallstartdate.setYear(year);
							c.setTime(fallstartdate);
							int dayOfWeek = c.get(Calendar.DAY_OF_WEEK);
							if (dayOfWeek == Calendar.FRIDAY) {
								c.add(Calendar.DATE, 3);
							} else if (dayOfWeek == Calendar.SATURDAY) {
								c.add(Calendar.DATE, 2);
							} else {
								c.add(Calendar.DATE, 1);
							}

							fallstartdate = c.getTime();

						}

						meetinginfo += " "
								+ new SimpleDateFormat("EEEE").format(c
										.getTime());

						if (timetrackor == 0) {
							timetrackor++;
							meetinginfo += timing1;
						} else {
							timetrackor = 0;
							meetinginfo += timing2;

						}
						schedule.setMeetinginfo(meetinginfo);

						sectionDB.insertSection(schedule);
						professortrackor++;
					

				}
				// }
				// year++;
				// }
			}

			processedDegreeCodes.add(degree.getCode());
		}
	}

	public void generateSchedule() {

		String timing1 = " 2.30 PM to 6.15 PM";
		String timing2 = " 6 PM to 9.30 PM";

		Date springstartdate = parseDate("01-01-2015");
		Date summerstartdate = parseDate("05-01-2015");
		Date fallstartdate = parseDate("09-01-2015");

		ArrayList<Schedule> schedules = new ArrayList<>();

		DepartmentDB departmentDB = new DepartmentDB();
		StudentDB studentDB = new StudentDB();
		FacultyDB facultyDB = new FacultyDB();
		CourseDB courseDB = new CourseDB();
		SectionDB sectionDB = new SectionDB();

		sectionDB.deleteSchedule("");
		String[] sems = new String[3];
		sems[0] = "SP";
		sems[1] = "SU";
		sems[2] = "FA";

		int year = 2015;

		int timetrackor = 0;

		ArrayList<Degree> degrees = departmentDB.getDepartments();
		ArrayList<String> processedDegreeCodes = new ArrayList<>();
		for (Degree degree : degrees) {

			String code = degree.getCode().replaceAll(".", "");

			ArrayList<String> courseids = departmentDB.getCoursesbyId(degree
					.getId());

			for (String courseid : courseids) {
				year = 2015;
				for (int k = 0; k < 2; k++) {
					for (int i = 0; i < sems.length; i++) {

						ArrayList<StudentCourse> studentcourses = studentDB
								.getStudentsbyCourse(year + sems[i], courseid);

						ArrayList<String> instructors = facultyDB
								.getFacultybyCourseId(courseid);

						int studentssize = studentcourses.size();

						int availablestudents = studentssize;
						int section = (studentssize / 25) + 1;

						if (studentssize % 25 == 0) {
							section = studentssize / 25;
						}
						int professortrackor = 0;
						for (int sectionindex = 0; sectionindex < section; sectionindex++) {
							if (instructors.size() - 1 >= professortrackor) {

								Schedule schedule = new Schedule();
								if (sems[i].equals("SP")) {
									schedule.setTerm("SPRING " + year);
								} else if (sems[i].equals("SU")) {
									schedule.setTerm("SUMMER " + year);
								} else if (sems[i].equals("FA")) {
									schedule.setTerm("FALL " + year);
								} else
									schedule.setTerm("FALL " + year);

								schedule.setStatus("Open");
								Course coursedetails = new Course();
								coursedetails = courseDB.getCourse(courseid);
								if (coursedetails == null)
									schedule.setSectionname(courseid + " ");
								else
									schedule.setSectionname(courseid + " "
											+ coursedetails.getCoursename());

								schedule.setMeetinginfo("");
								schedule.setFaculty(instructors
										.get(professortrackor));
//								if (sectionindex != (section - 1)) {
//									
//									schedule.setAvailable("25 / 25/ 0");
//								} else {
//
//									if ((studentssize % 25) > 0) {
//										schedule.setAvailable((studentssize % 25)
//												+ " / 25/ 0");
//									} else {
//										continue;
//									}
//								}
								
								if(availablestudents-25>0){
									schedule.setAvailable("25 / 25/ 0");
									availablestudents = availablestudents-25;
								}
								else{
									
									schedule.setAvailable(availablestudents+" / 25/ 0");
									
								}

								schedule.setCredits("3.00");
								schedule.setAcademiclevel("Graduate");

								springstartdate.setYear(year);
								String meetinginfo = "Lecture ";

								Calendar c = Calendar.getInstance();

								if (sems[i].equals("SP")) {

									springstartdate.setYear(year);
									c.setTime(springstartdate);
									int dayOfWeek = c.get(Calendar.DAY_OF_WEEK);
									if (dayOfWeek == Calendar.FRIDAY) {
										c.add(Calendar.DATE, 3);
									} else if (dayOfWeek == Calendar.SATURDAY) {
										c.add(Calendar.DATE, 2);
									} else {
										c.add(Calendar.DATE, 1);
									}

									springstartdate = c.getTime();

								} else if (sems[i].equals("SU")) {

									summerstartdate.setYear(year);
									c.setTime(summerstartdate);
									int dayOfWeek = c.get(Calendar.DAY_OF_WEEK);
									if (dayOfWeek == Calendar.FRIDAY) {
										c.add(Calendar.DATE, 3);
									} else if (dayOfWeek == Calendar.SATURDAY) {
										c.add(Calendar.DATE, 2);
									} else {
										c.add(Calendar.DATE, 1);
									}

									summerstartdate = c.getTime();
								} else {

									fallstartdate.setYear(year);
									c.setTime(fallstartdate);
									int dayOfWeek = c.get(Calendar.DAY_OF_WEEK);
									if (dayOfWeek == Calendar.FRIDAY) {
										c.add(Calendar.DATE, 3);
									} else if (dayOfWeek == Calendar.SATURDAY) {
										c.add(Calendar.DATE, 2);
									} else {
										c.add(Calendar.DATE, 1);
									}

									fallstartdate = c.getTime();

								}

								meetinginfo += " "
										+ new SimpleDateFormat("EEEE").format(c
												.getTime());

								if (timetrackor == 0) {
									timetrackor++;
									meetinginfo += timing1;
								} else {
									timetrackor = 0;
									meetinginfo += timing2;

								}
								schedule.setMeetinginfo(meetinginfo);

								sectionDB.insertSection(schedule);
								professortrackor++;
							}

						}
					}
					year++;
				}
			}

			processedDegreeCodes.add(degree.getCode());
		}
	}

	// public static void main(String[] args) {
	// ScheduleService scheduleService = new ScheduleService();
	// scheduleService.generateSchedule();
	// }
}
