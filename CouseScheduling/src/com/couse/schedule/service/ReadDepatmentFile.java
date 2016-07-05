package com.couse.schedule.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import com.course.schedule.db.DepartmentDB;
import com.couse.schedule.model.Course;
import com.couse.schedule.model.Degree;

public class ReadDepatmentFile {

	public void addDepartmentDetails(String filepath) {

		String filename = new File(filepath).getName();
		filename = filename.split("-")[1].substring(0,
				filename.split("-")[1].indexOf("POS"));
		System.out.println(filename);
		Degree degree = new Degree();
		degree.setName(filename);
		String code = "MSCS.SFTW.ENG";
		if (DepartmentMap.map.containsKey(filename.trim())) {
			code = DepartmentMap.map.get(filename.trim());
		}
		degree.setCode(code);
		DepartmentDB departmentDB = new DepartmentDB();
		int deptid = departmentDB.addDepartmentDetails(degree);

		FileInputStream fileInputStream = null;
		System.out.println("Testing");
		try {
			Workbook workbook = WorkbookFactory.create(new File(filepath));
			Sheet sheet = workbook.getSheetAt(0);
			for (int i = 12; i < sheet.getLastRowNum(); i++) {

				Row row = sheet.getRow(i);
				Cell cell = row.getCell(0);
				String cellValue = cell.getStringCellValue();
				if (cellValue.toLowerCase().contains("hours")) {
					continue;
				}

				if (cellValue.trim().equals(""))
					break;

				cellValue = cellValue.replaceAll(":", "").replaceAll(" ", "");

				Course course = new Course();
				course.setCouseid(cellValue);
				course.setDeptid(deptid);
				departmentDB.addCourseParticulars(course);
				System.out.println("Testing");

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
