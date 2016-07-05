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
import com.couse.schedule.model.Faculty;

public class FacultyDB {

	public ArrayList<String> getFacultybyCourseId(String courseid) {

		ArrayList<String> instructors = new ArrayList<>();

		try {
			Connection con = DB.getConnection();
			Statement statement = con.createStatement();
			String SQL = "select instructorname from instructorteachings where courseid='"
					+ courseid+"'";

			ResultSet resultSet = statement.executeQuery(SQL);
			while (resultSet.next()) {

				instructors.add(resultSet.getString("instructorname"));
			}

			con.close();
		} catch (Exception e) {

			e.printStackTrace();

		}
		return instructors;
	}

	public int addFacultyDetails(Faculty faculty) {

		int id = -1;
		try {
			Connection con = DB.getConnection();
			String insertQuery = "INSERT INTO instructors"
					+ "( fname,lname,cousespersemester) VALUES" + "(?,?,?)";
			PreparedStatement preparedStatement = con
					.prepareStatement(insertQuery);
			preparedStatement.setString(1, faculty.getFname());
			preparedStatement.setString(2, faculty.getLname());
			preparedStatement.setString(3, faculty.getCoursespersemister());

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

	public int addInstructorTeachings(Faculty faculty) {

		int id = -1;
		try {
			Connection con = DB.getConnection();
			String insertQuery = "INSERT INTO instructorteachings"
					+ "( instructorname,required,courseid) VALUES" + "(?,?,?)";
			PreparedStatement preparedStatement = con
					.prepareStatement(insertQuery);
			preparedStatement.setString(1, faculty.getFname());
			preparedStatement.setInt(2, faculty.getRequired());
			preparedStatement.setString(3, faculty.getCourseid());

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

	public void getFacultyParticulars(DefaultTableModel tableModel) {
		try {
			Connection con = DB.getConnection();
			Statement statement = con.createStatement();
			String SQL = "select I.id,instructorname,I.courseid,coursename from instructorteachings I,course where I.courseid = course.couseid";

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

	public Faculty getFaculty(String facultyId) {

		Faculty faculty = new Faculty();
		try {
			Connection con = DB.getConnection();
			Statement statement = con.createStatement();
			String SQL = "select * from instructors where id='" + facultyId
					+ "'";

			ResultSet resultSet = statement.executeQuery(SQL);
			if (resultSet.next()) {

				faculty.setFname(resultSet.getString("fname"));
				faculty.setLname(resultSet.getString("lname"));
				faculty.setCoursespersemister(resultSet
						.getString("cousespersemester"));
				faculty.setId(resultSet.getString("id"));

			} else {
				faculty = null;
			}

			con.close();
		} catch (Exception e) {

			e.printStackTrace();

		}
		return faculty;
	}

	public int updateFacultyDetails(Faculty faculty, String fid) {

		int id = -1;
		try {
			Connection con = DB.getConnection();
			String insertQuery = "update instructors "
					+ "set fname = ?,lname=?,cousespersemester=? where id=?";
			PreparedStatement preparedStatement = con
					.prepareStatement(insertQuery);
			preparedStatement.setString(1, faculty.getFname());
			preparedStatement.setString(2, faculty.getLname());
			preparedStatement.setString(3, faculty.getCoursespersemister());
			preparedStatement.setString(4, fid);

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

	public void deleteFaculty(String facultyid) {

		try {
			Connection con = DB.getConnection();
			String insertQuery = "delete from instructors" + " where id = "
					+ " ?";
			PreparedStatement preparedStatement = con
					.prepareStatement(insertQuery);
			preparedStatement.setString(1, facultyid);

			preparedStatement.executeUpdate();

			con.close();

		} catch (Exception ex) {
			ex.printStackTrace();

		}
	}

}
