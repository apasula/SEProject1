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
import com.course.schedule.db.DepartmentDB;
import com.couse.schedule.model.Course;
import com.couse.schedule.model.Degree;

public class AddDegreePanel extends JPanel {

	public AddDegreePanel() {
		init();
	}

	public void init() {
		setLayout(null);

		JLabel hint = new JLabel("Add Department");
		hint.setForeground(Color.blue);
		hint.setFont(new Font("Serif", Font.BOLD, 20));

		JLabel departmentlabel = new JLabel("Enter Department Name:");
		JLabel departmentcodelabel = new JLabel("Enter Department Code:");

		final JTextField departmenttf = new JTextField();
		final JTextField departmentcodetf = new JTextField();
		JButton submitbtn = new JButton("Submit");

		hint.setBounds(300, 30, 400, 30);
		departmentlabel.setBounds(200, 70, 200, 30);
		departmentcodelabel.setBounds(200, 110, 200, 30);
		departmenttf.setBounds(400, 70, 200, 30);
		departmentcodetf.setBounds(400, 110, 200, 30);
		submitbtn.setBounds(300, 160, 100, 30);

		submitbtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {

				if (departmenttf.getText().trim().equals("")
						|| departmentcodetf.getText().trim().equals("")) {

					JOptionPane.showMessageDialog(AddDegreePanel.this,
							"Please enter all fields");

				} else {
					Degree degree = new Degree();
					degree.setDepartment(departmenttf.getText());
					degree.setCode(departmentcodetf.getText());
					DepartmentDB departmentDB = new DepartmentDB();
					departmentDB.addDepartmentDetails(degree);

					JOptionPane.showMessageDialog(AddDegreePanel.this,
							"Degree Added");

				}

			}
		});

		add(hint);
		add(departmentlabel);
		add(departmenttf);
		add(departmentcodelabel);
		add(departmentcodetf);
		//
		add(submitbtn);

		setBackground(Color.WHITE);

	}
}
