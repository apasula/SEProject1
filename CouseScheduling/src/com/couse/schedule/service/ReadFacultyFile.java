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

import com.course.schedule.db.FacultyDB;
import com.couse.schedule.model.Course;
import com.couse.schedule.model.Faculty;

public class ReadFacultyFile {

	public void addFacultyDetails(String filepath) {

		FileInputStream fileInputStream = null;
		try {
			// fileInputStream = new FileInputStream(filepath);

			Workbook workbook = WorkbookFactory.create(new File(filepath));
			Sheet sheet = workbook.getSheetAt(2);
			Iterator<Row> rowIterator = sheet.iterator();

			int temp = 0;
			while (rowIterator.hasNext()) {
				Row row = rowIterator.next();

				if (temp == 0) {
					temp++;
					continue;
				}

				Cell cell1 = row.getCell((short) 0);
				String lastname = cell1.getStringCellValue();
				Cell cell2 = row.getCell((short) 1);
				String firstname = cell2.getStringCellValue();
				Cell cell3 = row.getCell((short) 2);
				double coursespersemister = cell3.getNumericCellValue();

				Faculty faculty = new Faculty();
				faculty.setLname(lastname);
				faculty.setFname(firstname);
				faculty.setCoursespersemister("" + coursespersemister);

				FacultyDB facultyDB = new FacultyDB();
				facultyDB.addFacultyDetails(faculty);

			}

			for (int i = 0; i < 2; i++) {
				sheet = workbook.getSheetAt(i);
				rowIterator = sheet.iterator();
				temp = 0;
				while (rowIterator.hasNext()) {

					Row row = rowIterator.next();
					if (temp == 0) {
						temp++;
						continue;
					}

					Cell cell1 = row.getCell((short) 0);
					if (cell1 == null)
						break;

					String courseId = cell1.getStringCellValue().replaceAll(
							" ", "");

					System.out.println(courseId);
					Cell cell2 = row.getCell((short) 2);
					if (cell2 == null)
						break;
					String required = cell2.getStringCellValue();
					int isRequired = 1;
					if (required.toLowerCase().equals("elective")) {
						isRequired = 0;
					}

					Faculty faculty = new Faculty();
					faculty.setCourseid(courseId);
					faculty.setRequired(isRequired);

					FacultyDB facultyDB = new FacultyDB();

					Cell cell4 = row.getCell((short) 3);
					String instructor1 = cell4.getStringCellValue();
					if (!instructor1.trim().equals("")) {
						faculty.setFname(instructor1.replaceAll("[-+.^:,]", ""));
						facultyDB.addInstructorTeachings(faculty);
					}

					Cell cell5 = row.getCell((short) 4);
					String instructor2 = cell5.getStringCellValue();
					if (!instructor2.trim().equals("")) {
						faculty.setFname(instructor2.replaceAll("[-+.^:,]", ""));
						facultyDB.addInstructorTeachings(faculty);
					}

					Cell cell6 = row.getCell((short) 5);
					String instructor3 = cell6.getStringCellValue();
					if (!instructor3.trim().equals("")) {
						faculty.setFname(instructor3.replaceAll("[-+.^:,]", ""));
						facultyDB.addInstructorTeachings(faculty);
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
