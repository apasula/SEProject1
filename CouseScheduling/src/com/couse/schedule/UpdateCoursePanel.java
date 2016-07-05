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
import com.couse.schedule.model.Course;

public class UpdateCoursePanel extends JPanel {

	public UpdateCoursePanel() {
		init();
	}
	String presentcourseid;

	public void init() {

		setLayout(null);

		JLabel courseidlabelview = new JLabel("Enter Course id:");
		final JTextField courseidtfview = new JTextField();
		JButton submitbtnview = new JButton("Submit");
		JLabel courseidlabel = new JLabel("Enter Course id:");
		JLabel coursenamelabel = new JLabel("Enter Course Name:");
		final JTextField courseidtf = new JTextField();
		final JTextField coursenametf = new JTextField();
		JButton submitbtn = new JButton("Submit");

		submitbtnview.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {

				if (courseidtfview.getText().trim().equals("")) {

					JOptionPane.showMessageDialog(UpdateCoursePanel.this,
							"Please enter course id");

				} else {
					CourseDB courseDB = new CourseDB();
					Course course = courseDB.getCourse(courseidtfview.getText()
							.trim());
					if (course == null) {

						JOptionPane.showMessageDialog(UpdateCoursePanel.this,
								"Invalid Course Id");

					} else {
						presentcourseid = course.getCouseid();
						System.out.println();
						courseidtf.setText(course.getCouseid());
						coursenametf.setText(course.getCoursename());
					}
				}

			}
		});

		JLabel hint = new JLabel("Update Course");
		hint.setForeground(Color.blue);
		hint.setFont(new Font("Serif", Font.BOLD, 20));

		
		courseidlabelview.setBounds(230, 10, 300, 30);
		courseidtfview.setBounds(330, 10, 150, 30);
		submitbtnview.setBounds(500, 10, 100, 30);

		hint.setBounds(300, 60, 400, 30);
		courseidlabel.setBounds(200, 100, 200, 30);
		coursenamelabel.setBounds(200, 150, 200, 30);
		courseidtf.setBounds(400, 100, 200, 30);
		coursenametf.setBounds(400, 140, 200, 30);
		submitbtn.setBounds(300, 180, 100, 30);

		submitbtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {

				if (courseidtf.getText().trim().equals("")
						|| courseidtf.getText().trim().equals("")) {

					JOptionPane.showMessageDialog(UpdateCoursePanel.this,
							"Please enter all fields");

				} else {
					Course course = new Course();
					course.setCouseid(courseidtf.getText());
					course.setCoursename(coursenametf.getText());
					course.setCoursedescription("");
					CourseDB courseDB = new CourseDB();
					courseDB.updateCouseDetails(course,presentcourseid);
					JOptionPane.showMessageDialog(UpdateCoursePanel.this,
							"Course Updated");
					course.setCouseid("");
					course.setCoursename("");
					course.setCoursedescription("");

				}

			}
		});

		add(hint);

		add(courseidlabelview);
		add(courseidtfview);
		add(submitbtnview);

		add(courseidlabel);
		add(courseidtf);
		add(coursenamelabel);
		add(coursenametf);
		add(submitbtn);

		setBackground(Color.WHITE);

	}
}
