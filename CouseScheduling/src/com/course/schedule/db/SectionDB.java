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
import com.couse.schedule.model.Schedule;
import com.couse.schedule.model.Section;
import com.couse.schedule.model.StudentCourse;

public class SectionDB {

	public int insertSection(Schedule section) {

		int id = -1;
		try {
			Connection con = DB.getConnection();
			String insertQuery = "INSERT INTO schedule"
					+ "( term,status,sectionname,meetinginfo,faculty,available,credits,academiclevel) VALUES"
					+ "(?,?,?,?,?,?,?,?)";
			PreparedStatement preparedStatement = con
					.prepareStatement(insertQuery);
			preparedStatement.setString(1, section.getTerm());
			preparedStatement.setString(2, section.getStatus());
			preparedStatement.setString(3, section.getSectionname());
			preparedStatement.setString(4, section.getMeetinginfo());
			preparedStatement.setString(5, section.getFaculty());
			preparedStatement.setString(6, section.getAvailable());
			preparedStatement.setString(7, section.getCredits());
			preparedStatement.setString(8, section.getAcademiclevel());

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
	
	
	public void getEnrollmentPerc(DefaultTableModel tableModel) {
		try {
			Connection con = DB.getConnection();
			Statement statement = con.createStatement();
			String SQL = "SELECT term,sectionname, CONCAT( ROUND( (SUBSTRING_INDEX( SUBSTRING_INDEX( available,  '/', 1 ) ,  '/', -1 ) / SUBSTRING_INDEX( SUBSTRING_INDEX( available,  '/', 2 ) ,  '/', -1 ) *100 ) , 2 ) ,  '%') AS enrollment FROM  `schedule`";

			System.out.println(SQL);
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
	
	

	

	public void getSchedule(DefaultTableModel tableModel) {
		try {
			Connection con = DB.getConnection();
			Statement statement = con.createStatement();
			String SQL = "select * from schedule order by term desc";

			ResultSet rs = statement.executeQuery(SQL);

			ResultSetMetaData metaData = rs.getMetaData();

			// Names of columns
			Vector<String> columnNames = new Vector<String>();
			int columnCount = metaData.getColumnCount();
			for (int i = 1; i <= columnCount; i++) {
				//if (i == 1)
					//continue;
				columnNames.add(metaData.getColumnName(i));
			}

			// Data of the table
			Vector<Vector<Object>> data = new Vector<Vector<Object>>();
			while (rs.next()) {
				Vector<Object> vector = new Vector<Object>();
				for (int i = 1; i <= columnCount; i++) {
				//	if (i == 1)
					//	continue;

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

	public ArrayList<Schedule> readSchedule() {

		ArrayList<Schedule> schedules = new ArrayList<>();
		try {
			Connection con = DB.getConnection();
			Statement statement = con.createStatement();
			String SQL = "select * from schedule order by term desc";

			ResultSet resultSet = statement.executeQuery(SQL);
			while (resultSet.next()) {

				Schedule schedule = new Schedule();
				schedule.setTerm(resultSet.getString("term"));
				schedule.setStatus(resultSet.getString("status"));
				schedule.setSectionname(resultSet.getString("sectionname"));
				schedule.setMeetinginfo(resultSet.getString("meetinginfo"));
				schedule.setFaculty(resultSet.getString("faculty"));
				schedule.setId(resultSet.getInt("id"));
				schedule.setCredits(resultSet.getString("credits"));
				schedule.setAvailable(resultSet.getString("available"));
				schedule.setAcademiclevel(resultSet.getString("academiclevel"));
				schedules.add(schedule);
			}

			con.close();
		} catch (Exception e) {

			e.printStackTrace();

		}

		return schedules;
	}
	
	public void updateSectionDetails(Schedule schedule, String id) {

		try {
			Connection con = DB.getConnection();
			String insertQuery = "update schedule "
					+ "set meetinginfo = ?,faculty=?,credits=? where id=?";
			PreparedStatement preparedStatement = con
					.prepareStatement(insertQuery);
			preparedStatement.setString(1, schedule.getMeetinginfo());
			preparedStatement.setString(2, schedule.getFaculty());
			preparedStatement.setString(3, schedule.getCredits());
			preparedStatement.setString(4, id);

			System.out.println(preparedStatement);
			preparedStatement.executeUpdate();

			ResultSet rs = preparedStatement.getGeneratedKeys();

			
			con.close();

		} catch (Exception ex) {
			ex.printStackTrace();

		}
	}


	public void deleteSchedule(String sem) {
		try {
			Connection con = DB.getConnection();
			String insertQuery="delete from schedule";
			if(!sem.equals(""))
			 insertQuery = "delete from schedule where term ='"+sem+"'";
			PreparedStatement preparedStatement = con
					.prepareStatement(insertQuery);

			preparedStatement.executeUpdate();

			con.close();

		} catch (Exception ex) {
			ex.printStackTrace();

		}
	}
}
