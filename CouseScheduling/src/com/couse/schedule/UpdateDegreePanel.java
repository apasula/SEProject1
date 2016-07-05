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

public class UpdateDegreePanel extends JPanel {

	public UpdateDegreePanel() {
		init();
	}

	String presentdegreeid;

	public void init() {

		setLayout(null);

		JLabel courseidlabelview = new JLabel("Enter Degree code:");
		final JTextField courseidtfview = new JTextField();
		JButton submitbtnview = new JButton("Submit");

		JLabel departmentlabel = new JLabel("Enter Department Name:");
		JLabel departmentcodelabel = new JLabel("Enter Department Code:");

		final JTextField departmenttf = new JTextField();
		final JTextField departmentcodetf = new JTextField();

		JButton submitbtn = new JButton("Submit");

		submitbtnview.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {

				if (courseidtfview.getText().trim().equals("")) {

					JOptionPane.showMessageDialog(UpdateDegreePanel.this,
							"Please enter department code");

				} else {
					DepartmentDB departmentDB = new DepartmentDB();
					Degree degree = departmentDB.getDegree(courseidtfview
							.getText().trim());
					if (degree == null) {

						JOptionPane.showMessageDialog(UpdateDegreePanel.this,
								"Invalid department code");

					} else {
						presentdegreeid = degree.getCode();
						System.out.println();
						departmentcodetf.setText(degree.getCode());
						departmenttf.setText(degree.getDepartment());
					}
				}

			}
		});

		JLabel hint = new JLabel("Update Department");
		hint.setForeground(Color.blue);
		hint.setFont(new Font("Serif", Font.BOLD, 20));

		courseidlabelview.setBounds(230, 10, 300, 30);
		courseidtfview.setBounds(330, 10, 150, 30);
		submitbtnview.setBounds(500, 10, 100, 30);

		hint.setBounds(300, 60, 400, 30);
		departmentcodelabel.setBounds(200, 100, 200, 30);
		departmentlabel.setBounds(200, 150, 200, 30);
		departmentcodetf.setBounds(400, 100, 200, 30);
		departmenttf.setBounds(400, 140, 200, 30);
		submitbtn.setBounds(300, 180, 100, 30);

		submitbtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {

				if (departmenttf.getText().trim().equals("")
						|| departmentcodetf.getText().trim().equals("")) {

					JOptionPane.showMessageDialog(UpdateDegreePanel.this,
							"Please enter all fields");

				} else {
					Degree degree = new Degree();
					degree.setDepartment(departmenttf.getText());
					degree.setCode(departmentcodetf.getText());
					DepartmentDB departmentDB = new DepartmentDB();
					departmentDB.updateDepartmentDetails(degree,
							presentdegreeid);
					JOptionPane.showMessageDialog(UpdateDegreePanel.this,
							"Department Updated");

				}

			}
		});

		add(hint);

		add(courseidlabelview);
		add(courseidtfview);
		add(submitbtnview);

		add(departmentlabel);
		add(departmenttf);
		add(departmentcodelabel);
		add(departmentcodetf);
		add(submitbtn);

		setBackground(Color.WHITE);
	}
}
