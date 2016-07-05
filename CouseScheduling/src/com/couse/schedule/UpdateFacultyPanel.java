package com.couse.schedule;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.course.schedule.db.FacultyDB;
import com.couse.schedule.model.Faculty;

public class UpdateFacultyPanel extends JPanel {

	public UpdateFacultyPanel() {
		init();
	}

	String presentfacultyid;

	public void init() {

		setLayout(null);

		JLabel facultyidlabelview = new JLabel("Enter Faculty id:");
		final JTextField facultyidtfview = new JTextField();
		JButton submitbtnview = new JButton("Submit");
		JLabel firstnamelabel = new JLabel("Enter First Name:");
		JLabel lastnamelabel = new JLabel("Enter Last Name:");
		JLabel cousespersemesterlabel = new JLabel("Courses Per Semester:");

		final JTextField firstnametf = new JTextField();
		final JTextField lastnametf = new JTextField();
		final JTextField cousespersemesterlabeltf = new JTextField();

		JButton submitbtn = new JButton("Submit");

		submitbtnview.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {

				if (facultyidtfview.getText().trim().equals("")) {

					JOptionPane.showMessageDialog(UpdateFacultyPanel.this,
							"Please enter faculty id");

				} else {
					FacultyDB facultyDB = new FacultyDB();
					Faculty faculty = facultyDB.getFaculty(facultyidtfview
							.getText().trim());
					if (faculty == null) {

						JOptionPane.showMessageDialog(UpdateFacultyPanel.this,
								"Invalid faculty Id");

					} else {
						presentfacultyid = "" + faculty.getId();
						System.out.println();
						firstnametf.setText(faculty.getFname());
						lastnametf.setText(faculty.getLname());
						cousespersemesterlabeltf.setText(faculty.getCoursespersemister());

					}
				}

			}
		});

		JLabel hint = new JLabel("Update faculty");
		hint.setForeground(Color.blue);
		hint.setFont(new Font("Serif", Font.BOLD, 20));

		facultyidlabelview.setBounds(230, 10, 300, 30);
		facultyidtfview.setBounds(330, 10, 150, 30);
		submitbtnview.setBounds(500, 10, 100, 30);

		hint.setBounds(300, 60, 400, 30);
		firstnamelabel.setBounds(200, 110, 200, 30);
		lastnamelabel.setBounds(200, 150, 200, 30);
		cousespersemesterlabel.setBounds(200, 190, 200, 30);

		firstnametf.setBounds(400, 110, 200, 30);
		lastnametf.setBounds(400, 150, 200, 30);
		cousespersemesterlabeltf.setBounds(400, 190, 200, 30);
		submitbtn.setBounds(300, 240, 100, 30);

		submitbtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {

				if (firstnametf.getText().trim().equals("")
						|| lastnametf.getText().trim().equals("")
						|| cousespersemesterlabeltf.getText().trim().equals("")) {

					JOptionPane.showMessageDialog(UpdateFacultyPanel.this,
							"Please enter all fields");

				} else {
					Faculty faculty = new Faculty();
					faculty.setFname(firstnametf.getText());
					faculty.setLname(lastnametf.getText());
					faculty.setCoursespersemister(cousespersemesterlabeltf
							.getText());
					FacultyDB facultyDB = new FacultyDB();
					facultyDB.updateFacultyDetails(faculty,presentfacultyid);
					JOptionPane.showMessageDialog(UpdateFacultyPanel.this,
							"Faculty Updated");
					firstnametf.setText("");
					lastnametf.setText("");
					cousespersemesterlabeltf.setText("");

				}

			}
		});

		add(hint);

		add(facultyidlabelview);
		add(facultyidtfview);
		add(submitbtnview);

		add(firstnamelabel);
		add(firstnametf);
		add(lastnamelabel);
		add(lastnametf);
		add(cousespersemesterlabel);
		add(cousespersemesterlabeltf);
		
		
		add(submitbtn);

		setBackground(Color.WHITE);

	}
}
