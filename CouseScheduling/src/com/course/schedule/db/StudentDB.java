package com.course.schedule.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.table.DefaultTableModel;

import com.couse.schedule.model.Degree;
import com.couse.schedule.model.Student;
import com.couse.schedule.model.StudentCourse;

public class StudentDB {

	public int addStudentCourseDetails(StudentCourse studentCourse) {

		int id = -1;
		try {
			Connection con = DB.getConnection();
			String insertQuery = "INSERT INTO studentcourses"
					+ "( studentid,year,courseid,attr,deptid) VALUES"
					+ "(?,?,?,?,?)";
			PreparedStatement preparedStatement = con
					.prepareStatement(insertQuery);
			preparedStatement.setString(1, studentCourse.getStudentId());
			preparedStatement.setString(2, studentCourse.getYear());
			preparedStatement.setString(3, studentCourse.getCouseId());
			preparedStatement.setString(4, studentCourse.getAttr());
			preparedStatement.setString(5, studentCourse.getDepid());

			preparedStatement.executeUpdate();

			ResultSet rs = preparedStatement.getGeneratedKeys();
			if (rs.next()) {
				id = rs.getInt(1);
			}

			con.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return id;
	}
	
	


	
	public ArrayList<StudentCourse> getStudentsbyCourse(String year,String courseid) {

		ArrayList<StudentCourse> studentCourses = new ArrayList<>();

		try {
			Connection con = DB.getConnection();
			Statement statement = con.createStatement();
			String SQL = "select * from studentcourses where courseid='"+courseid+"' and year = '"+year+"'";

			ResultSet resultSet = statement.executeQuery(SQL);
			while (resultSet.next()) {

				StudentCourse student = new StudentCourse();
				student.setStudentId(resultSet.getString("studentid"));
				student.setYear(resultSet.getString("year"));
				studentCourses.add(student);
			}

			con.close();
		} catch (Exception e) {

			e.printStackTrace();

		}
		return studentCourses;
	}

	public int addStudentDetails(StudentCourse studentCourse) {

		int id = -1;
		try {
			Connection con = DB.getConnection();
			String insertQuery = "INSERT INTO student"
					+ "( studentid,deptid,year) VALUES" + "(?,?,?)";
			PreparedStatement preparedStatement = con
					.prepareStatement(insertQuery);
			preparedStatement.setString(1, studentCourse.getStudentId());
			preparedStatement.setString(2, studentCourse.getDepid());
			preparedStatement.setString(3, studentCourse.getYear());

			System.out.println(preparedStatement);
			preparedStatement.executeUpdate();

			ResultSet rs = preparedStatement.getGeneratedKeys();
			if (rs.next()) {
				id = rs.getInt(1);
			}

			con.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return id;
	}
	
	

	public void getNewStudents(DefaultTableModel tableModel) {
		int id = -1;
		try {
			Connection con = DB.getConnection();
			Statement statement = con.createStatement();
			String SQL = "select studentid,year,deptid from student";

			ResultSet rs = statement.executeQuery(SQL);

			ResultSetMetaData metaData = rs.getMetaData();

			// Names of columns
			Vector<String> columnNames = new Vector<String>();
			int columnCount = metaData.getColumnCount();
			for (int i = 1; i <= columnCount; i++) {
				columnNames.add(metaData.getColumnName(i));
			}

			// Data of the table
			Vector<Vector<Object>> data = new Vector<Vector<Object>>();
			while (rs.next()) {
				Vector<Object> vector = new Vector<Object>();
				for (int i = 1; i <= columnCount; i++) {
					vector.add(rs.getObject(i));
				}
				data.add(vector);
			}

			tableModel.setDataVector(data, columnNames);
			rs.close();
			statement.close();

			con.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void getStudentsCourses(DefaultTableModel tableModel) {
		try {
			Connection con = DB.getConnection();
			Statement statement = con.createStatement();
			String SQL = "select studentid,year,courseid,attr from studentcourses";

			ResultSet rs = statement.executeQuery(SQL);

			ResultSetMetaData metaData = rs.getMetaData();

			// Names of columns
			Vector<String> columnNames = new Vector<String>();
			int columnCount = metaData.getColumnCount();
			for (int i = 1; i <= columnCount; i++) {
				columnNames.add(metaData.getColumnName(i));
			}

			// Data of the table
			Vector<Vector<Object>> data = new Vector<Vector<Object>>();
			while (rs.next()) {
				Vector<Object> vector = new Vector<Object>();
				for (int i = 1; i <= columnCount; i++) {
					vector.add(rs.getObject(i));
				}
				data.add(vector);
			}

			tableModel.setDataVector(data, columnNames);
			rs.close();
			statement.close();

			con.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
