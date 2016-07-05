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

import com.course.schedule.db.CourseDB;
import com.course.schedule.db.FacultyDB;
import com.couse.schedule.model.Course;
import com.couse.schedule.model.Faculty;

public class AddFacultyPanel extends JPanel {

	public AddFacultyPanel() {
		init();
	}

	public void init() {

		setLayout(null);

		JLabel hint = new JLabel("Add Faculty");
		hint.setForeground(Color.blue);
		hint.setFont(new Font("Serif", Font.BOLD, 20));
		JLabel idlabel = new JLabel("Enter Id:");
		JLabel firstnamelabel = new JLabel("Enter First Name:");
		JLabel lastnamelabel = new JLabel("Enter Last Name:");
		JLabel cousespersemesterlabel = new JLabel("Courses Per Semester:");
	
		final JTextField idtf = new JTextField();
		final JTextField firstnametf = new JTextField();
		final JTextField lastnametf = new JTextField();
		final JTextField cousespersemesterlabeltf = new JTextField();
		JButton submitbtn = new JButton("Submit");

		hint.setBounds(300, 30, 400, 30);
		
		idlabel.setBounds(200, 70, 200, 30);
		firstnamelabel.setBounds(200, 110, 200, 30);
		lastnamelabel.setBounds(200, 150, 200, 30);
		cousespersemesterlabel.setBounds(200, 190, 200, 30);

		idtf.setBounds(400, 70, 200, 30);
		firstnametf.setBounds(400, 110, 200, 30);
		lastnametf.setBounds(400, 150, 200, 30);
		cousespersemesterlabeltf.setBounds(400, 190, 200, 30);

		submitbtn.setBounds(300, 230, 100, 30);

		submitbtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {

				if (firstnametf.getText().trim().equals("")
						|| lastnametf.getText().trim().equals("") || cousespersemesterlabeltf.getText().trim().equals("")) {

					JOptionPane.showMessageDialog(AddFacultyPanel.this,
							"Please enter all fields");

				} else {
					Faculty faculty = new Faculty();
					faculty.setFname(firstnametf.getText());
					faculty.setLname(lastnametf.getText());
					faculty.setCoursespersemister(cousespersemesterlabeltf.getText());
					FacultyDB facultyDB = new FacultyDB();
					facultyDB.addFacultyDetails(faculty);
					JOptionPane.showMessageDialog(AddFacultyPanel.this,
							"Faculty Added");
					firstnametf.setText("");
					lastnametf.setText("");
					cousespersemesterlabeltf.setText("");

				}

			}
		});

		add(hint);
		//add(idlabel);
		//add(idtf);
		
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
