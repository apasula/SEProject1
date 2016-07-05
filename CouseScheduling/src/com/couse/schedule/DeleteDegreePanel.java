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

public class DeleteDegreePanel extends JPanel {

	public DeleteDegreePanel() {
		init();
	}

	public void init() {

		setLayout(null);

		JLabel hint = new JLabel("Delete Degree");
		hint.setForeground(Color.blue);
		hint.setFont(new Font("Serif", Font.BOLD, 20));

		JLabel courseidlabel = new JLabel("Enter Department code:");
		final JTextField courseidtf = new JTextField();
		JButton submitbtn = new JButton("Submit");

		hint.setBounds(300, 30, 400, 30);
		courseidlabel.setBounds(200, 70, 200, 30);
		courseidtf.setBounds(400, 70, 200, 30);
		submitbtn.setBounds(300, 160, 100, 30);

		submitbtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {

				if (courseidtf.getText().trim().equals("")
						|| courseidtf.getText().trim().equals("")) {

					JOptionPane.showMessageDialog(DeleteDegreePanel.this,
							"Please enter degree code");

				} else {
					DepartmentDB departmentDB = new DepartmentDB();
					departmentDB.deleteDepartment(courseidtf.getText().trim());
					JOptionPane.showMessageDialog(DeleteDegreePanel.this,
							"Degree Deleted");

				}

			}
		});

		add(hint);
		add(courseidlabel);
		add(courseidtf);
		add(submitbtn);

		setBackground(Color.WHITE);
	}
}