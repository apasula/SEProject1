package com.course.schedule.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.table.DefaultTableModel;

import com.couse.schedule.model.Course;
import com.couse.schedule.model.Degree;
import com.couse.schedule.model.StudentCourse;

public class DepartmentDB {

	public int addDepartmentDetails(Degree degree) {

		int id = -1;
		try {
			Connection con = DB.getConnection();
			String insertQuery = "INSERT INTO departments"
					+ "( departmentname,code) VALUES" + "(?,?)";
			PreparedStatement preparedStatement = con
					.prepareStatement(insertQuery);
			preparedStatement.setString(1, degree.getName());
			preparedStatement.setString(2, degree.getCode());

			preparedStatement.executeUpdate();

			System.out.println(preparedStatement);
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

	public void deleteDepartment(String code) {

		try {
			Connection con = DB.getConnection();
			String insertQuery = "delete from departments" + " where code = "
					+ " ?";
			PreparedStatement preparedStatement = con
					.prepareStatement(insertQuery);
			preparedStatement.setString(1, code);

			preparedStatement.executeUpdate();

			con.close();

		} catch (Exception ex) {
			ex.printStackTrace();

		}
	}

	public Degree getDegree(String code) {

		Degree degree = new Degree();
		try {
			Connection con = DB.getConnection();
			Statement statement = con.createStatement();
			String SQL = "select * from departments where code='" + code + "'";

			ResultSet resultSet = statement.executeQuery(SQL);
			if (resultSet.next()) {

				degree.setDepartment(resultSet.getString("departmentname"));
				degree.setCode(resultSet.getString("code"));

			} else {
				degree = null;
			}

			con.close();
		} catch (Exception e) {

			e.printStackTrace();

		}
		return degree;
	}

	public int updateDepartmentDetails(Degree degree, String courseid) {

		int id = -1;
		try {
			Connection con = DB.getConnection();
			String insertQuery = "update departments "
					+ "set departmentname = ? where code=?";
			PreparedStatement preparedStatement = con
					.prepareStatement(insertQuery);
			preparedStatement.setString(1, degree.getDepartment());
			preparedStatement.setString(2, courseid);

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

	public void getCoursePaticulars(DefaultTableModel tableModel) {
		try {
			Connection con = DB.getConnection();
			Statement statement = con.createStatement();
			String SQL = "select distinct courseparticulars.courseid,departmentname,course.coursename from courseparticulars,departments,course where courseparticulars.courseid = course.couseid and departments.id = courseparticulars.deptid";

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

	public ArrayList<String> getCoursesbyId(String courseid) {

		ArrayList<String> courses = new ArrayList<>();

		try {
			Connection con = DB.getConnection();
			Statement statement = con.createStatement();
			String SQL = "select courseid from courseparticulars where deptid="
					+ courseid;

			System.out.println(SQL);
			ResultSet resultSet = statement.executeQuery(SQL);
			while (resultSet.next()) {

				courses.add(resultSet.getString("courseid"));
			}

			con.close();
		} catch (Exception e) {

			e.printStackTrace();

		}
		return courses;
	}

	public ArrayList<Degree> getDepartments() {

		ArrayList<Degree> degrees = new ArrayList<>();

		try {
			Connection con = DB.getConnection();
			Statement statement = con.createStatement();
			String SQL = "select * from departments";

			ResultSet resultSet = statement.executeQuery(SQL);
			while (resultSet.next()) {

				Degree degree = new Degree();
				degree.setCode(resultSet.getString("code"));
				degree.setDepartment(resultSet.getString("departmentname"));
				degree.setId(resultSet.getString("id"));
				degrees.add(degree);
			}

			con.close();
		} catch (Exception e) {

			e.printStackTrace();

		}
		return degrees;
	}

	public int addCourseParticulars(Course course) {
		int id = -1;
		try {
			Connection con = DB.getConnection();
			String insertQuery = "INSERT INTO courseparticulars"
					+ "( courseid,deptid) VALUES" + "(?,?)";
			PreparedStatement preparedStatement = con
					.prepareStatement(insertQuery);
			preparedStatement.setString(1, course.getCouseid());
			preparedStatement.setInt(2, course.getDeptid());
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

}
