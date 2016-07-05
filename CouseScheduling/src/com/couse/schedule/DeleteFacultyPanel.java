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

public class DeleteFacultyPanel extends JPanel{

	public DeleteFacultyPanel(){
		init();
	}
	
	public void init(){
		setLayout(null);

		JLabel hint = new JLabel("Delete Faculty");
		hint.setForeground(Color.blue);
		hint.setFont(new Font("Serif", Font.BOLD, 20));

		JLabel courseidlabel = new JLabel("Enter Faculty id:");
		final JTextField facultyidtf = new JTextField();
		JButton submitbtn = new JButton("Submit");

		hint.setBounds(300, 30, 400, 30);
		courseidlabel.setBounds(200, 70, 200, 30);
		facultyidtf.setBounds(400, 70, 200, 30);
		submitbtn.setBounds(300, 160, 100, 30);

		submitbtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {

				if (facultyidtf.getText().trim().equals("")
						|| facultyidtf.getText().trim().equals("")) {

	            	JOptionPane.showMessageDialog(DeleteFacultyPanel.this, "Please enter faculty id");

				} else {
					FacultyDB facultyDB = new FacultyDB();
					facultyDB.deleteFaculty(facultyidtf.getText().trim());
	            	JOptionPane.showMessageDialog(DeleteFacultyPanel.this, "Faculty Deleted");
	          	
				}
				

			}
		});

		add(hint);
		add(courseidlabel);
		add(facultyidtf);
		add(submitbtn);

		setBackground(Color.WHITE);
	}
}