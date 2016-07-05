package com.couse.schedule;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.course.schedule.db.CourseDB;
import com.couse.schedule.model.Course;
import com.couse.schedule.service.UserLogin;

public class AddCoursePanel extends JPanel {

	
	public AddCoursePanel(){
		init();
	}
	
	public void init() {
		setLayout(null);

		JLabel hint = new JLabel("Add Course");
		hint.setForeground(Color.blue);
		hint.setFont(new Font("Serif", Font.BOLD, 20));

		JLabel courseidlabel = new JLabel("Enter Course id:");
		JLabel coursenamelabel = new JLabel("Enter Course Name:");
		final JTextField courseidtf = new JTextField();
		final JTextField coursenametf = new JTextField();
		JButton submitbtn = new JButton("Submit");

		hint.setBounds(300, 30, 400, 30);
		courseidlabel.setBounds(200, 70, 200, 30);
		coursenamelabel.setBounds(200, 110, 200, 30);
		courseidtf.setBounds(400, 70, 200, 30);
		coursenametf.setBounds(400, 110, 200, 30);
		submitbtn.setBounds(300, 160, 100, 30);

		submitbtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {

				if (courseidtf.getText().trim().equals("")
						|| coursenametf.getText().trim().equals("")) {

	            	JOptionPane.showMessageDialog(AddCoursePanel.this, "Please enter all fields");

				} else {
					Course course = new Course();
					course.setCouseid(courseidtf.getText());
					course.setCoursename(coursenametf.getText());
					course.setCoursedescription("");
					CourseDB courseDB = new CourseDB();
					courseDB.addCouseDetails(course);
	            	JOptionPane.showMessageDialog(AddCoursePanel.this, "Course Added");
	            	course.setCouseid("");
					course.setCoursename("");
					course.setCoursedescription("");
				
					
				}
				

			}
		});

		add(hint);
		add(courseidlabel);
		add(courseidtf);
		add(coursenamelabel);
		add(coursenametf);
		add(submitbtn);

		setBackground(Color.WHITE);

	}

}
