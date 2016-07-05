package com.couse.schedule.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import com.course.schedule.db.CourseDB;
import com.couse.schedule.model.Course;
import com.couse.schedule.model.Degree;

public class ReadCourseFile {

	public void addCourseDetails(String filepath) {

		FileInputStream fileInputStream = null;
		try {
			// fileInputStream = new FileInputStream(filepath);

			Workbook workbook = WorkbookFactory.create(new File(filepath));
			for (int i = 0; i < workbook.getNumberOfSheets(); i++) {

				Degree degree = new Degree();
				System.out.println("Sheet Name" + workbook.getSheetName(i));
				degree.setName(workbook.getSheetName(i));
				// DepartmentDB departmentDB = new DepartmentDB();
				// int deptid = departmentDB.addDepartmentDetails(degree);

				Sheet sheet = workbook.getSheetAt(i);
				Iterator<Row> rowIterator = sheet.iterator();

				while (rowIterator.hasNext()) {
					Row row = rowIterator.next();

					Iterator<Cell> cellIterator = row.cellIterator();
					int trackor = 0;
					Course course = new Course();
					while (cellIterator.hasNext()) {

						Cell cell = cellIterator.next();
						if (cell.getStringCellValue().equals("Course Name")) {
							break;
						}
						if (trackor == 0) {

							String cellValue = cell.getStringCellValue();
							cellValue = cellValue.replaceAll("-", " ").replaceAll(":", "");
							System.out.println(cellValue);
							String[] values = cellValue.split(" ");
							course.setCouseid(values[0] + values[1]);
							course.setCoursename(cellValue.replace(values[0]
									+ " " + values[1], ""));
							course.setCoursename(course.getCoursename().replaceAll(":",""));
						}

						else {

							course.setCoursedescription(cell
									.getStringCellValue());
							CourseDB courseDB = new CourseDB();
							courseDB.addCouseDetails(course);
							System.out.println("");
						}

						trackor++;
					}

				
				}

			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (fileInputStream != null) {
				try {
					fileInputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}


}
