package com.course.schedule.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.Vector;

import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.couse.schedule.model.Course;
import com.couse.schedule.model.StudentCourse;

public class CourseDB {

	public int addCouseDetails(Course course) {

		int id = -1;
		try {
			Connection con = DB.getConnection();
			String insertQuery = "INSERT INTO course"
					+ "( coursename,couseid,coursedescription) VALUES"
					+ "(?,?,?)";
			PreparedStatement preparedStatement = con
					.prepareStatement(insertQuery);
			preparedStatement.setString(1, course.getCoursename());
			preparedStatement.setString(2, course.getCouseid());
			preparedStatement.setString(3, course.getCoursedescription());

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

	public Course getCourse(String courseid) {

		Course course = new Course();
		try {
			Connection con = DB.getConnection();
			Statement statement = con.createStatement();
			String SQL = "select * from course where couseid='" + courseid
					+ "'";

			ResultSet resultSet = statement.executeQuery(SQL);
			if (resultSet.next()) {

				course.setCouseid(resultSet.getString("couseid"));
				course.setCoursename(resultSet.getString("coursename"));

			} else {
				course = null;
			}

			con.close();
		} catch (Exception e) {

			e.printStackTrace();

		}
		return course;
	}

	public int updateCouseDetails(Course course, String courseid) {

		int id = -1;
		try {
			Connection con = DB.getConnection();
			String insertQuery = "update course "
					+ "set coursename = ?,couseid=? where couseid=?";
			PreparedStatement preparedStatement = con
					.prepareStatement(insertQuery);
			preparedStatement.setString(1, course.getCoursename());
			preparedStatement.setString(2, course.getCouseid());
			preparedStatement.setString(3, courseid);

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

	public void deleteCourse(String courseid) {

		try {
			Connection con = DB.getConnection();
			String insertQuery = "delete from course" + " where couseid = "
					+ " ?";
			PreparedStatement preparedStatement = con
					.prepareStatement(insertQuery);
			preparedStatement.setString(1, courseid);

			preparedStatement.executeUpdate();

			con.close();

		} catch (Exception ex) {
			ex.printStackTrace();

		}
	}

	public void getCourses(DefaultTableModel tableModel) {
		try {
			Connection con = DB.getConnection();
			Statement statement = con.createStatement();
			String SQL = "select * from course";

			ResultSet rs = statement.executeQuery(SQL);

			ResultSetMetaData metaData = rs.getMetaData();

			// Names of columns
			Vector<String> columnNames = new Vector<String>();
			int columnCount = metaData.getColumnCount();
			for (int i = 1; i <= columnCount; i++) {
				if (i == 1)
					continue;
				columnNames.add(metaData.getColumnName(i));
			}

			// Data of the table
			Vector<Vector<Object>> data = new Vector<Vector<Object>>();
			while (rs.next()) {
				Vector<Object> vector = new Vector<Object>();
				for (int i = 1; i <= columnCount; i++) {
					if (i == 1)
						continue;

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
