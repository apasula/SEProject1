package com.couse.schedule.service;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import com.couse.schedule.model.StudentCourse;

public class ReadCSVFile {

	public ArrayList<StudentCourse> readCouseDetails(String filepath) {

		ArrayList<StudentCourse> studentCourses = new ArrayList<>();

		BufferedReader br = null;
		String line = "";
		String cvsSplitBy = ",";

		try {

			br = new BufferedReader(new FileReader(filepath));
			while ((line = br.readLine()) != null) {

				String[] coursearray = line.split(cvsSplitBy);
				StudentCourse studentCourse = new StudentCourse();
				studentCourse.setStudentId(coursearray[0].replaceAll("[-+.^:,'\"﻿]",""));
				studentCourse.setCouseId(coursearray[1].replaceAll(" ", "").replaceAll("[-+.^:,'\"]",""));
				studentCourse.setYear(coursearray[3].replaceAll("[-+.^:,'\"]",""));
				studentCourse.setAttr(coursearray[4].replaceAll("[-+.^:,'\"]",""));
				studentCourses.add(studentCourse);

			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		return studentCourses;
	}

	public ArrayList<StudentCourse> readStudentDetails(String filepath) {

		ArrayList<StudentCourse> studentCourses = new ArrayList<StudentCourse>();

		BufferedReader br = null;
		String line = "";
		String cvsSplitBy = ",";

		try {

			br = new BufferedReader(new FileReader(filepath));
			while ((line = br.readLine()) != null) {

				String[] coursearray = line.split(cvsSplitBy);
				StudentCourse studentCourse = new StudentCourse();
				studentCourse.setStudentId(coursearray[0].replaceAll("[-+.^:,'\"﻿]",""));
				studentCourse.setDepid(coursearray[1].replaceAll(" ", "").replaceAll("[-+.^:,'\"]",""));
				studentCourse.setYear(coursearray[2].replaceAll("[-+.^:,'\"]",""));
			
				studentCourses.add(studentCourse);
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		return studentCourses;

	}
}
