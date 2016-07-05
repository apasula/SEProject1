package com.couse.schedule;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import com.couse.schedule.service.ScheduleService;

public class DirectorHomePanel extends Panel {

	public DirectorHomePanel() {
		init();
	}

	public void init() {
		JPanel mainPanel = new JPanel(new BorderLayout());
		mainPanel.setPreferredSize(new Dimension(400, 500));

		JPanel topPnl = new JPanel();
		JPanel btnPnl = new JPanel(new FlowLayout(FlowLayout.CENTER));

		btnPnl.setBorder(BorderFactory.createEmptyBorder(50, 10, 10, 10));

		JButton courseBtn = new JButton("Course");
		JButton facultyBtn = new JButton("Faculty");
		JButton degreeBtn = new JButton("Degree");
		JButton importDataBtn = new JButton("Import Data");
		final JButton scheduleGenerateBtn = new JButton("Schedule Generate");
		final JButton viewScheduleBtn = new JButton("View/Edit Schedule");
		final JButton testScheduleBtn = new JButton("Test Schedule");

		courseBtn.setPreferredSize(new Dimension(150, 40));
		facultyBtn.setPreferredSize(new Dimension(150, 40));
		degreeBtn.setPreferredSize(new Dimension(150, 40));
		importDataBtn.setPreferredSize(new Dimension(150, 40));
		scheduleGenerateBtn.setPreferredSize(new Dimension(150, 40));
		viewScheduleBtn.setPreferredSize(new Dimension(150, 40));
		testScheduleBtn.setPreferredSize(new Dimension(150, 40));

		courseBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {

				MainFrame.showCard("admincoursepanel");
			}
		});

		facultyBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				MainFrame.showCard("adminfacultypanel");

			}

		});

		degreeBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				MainFrame.showCard("admindegreepanel");

			}
		});

		importDataBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				MainFrame.showCard("adminimportdata");

			}
		});

		scheduleGenerateBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {

//				scheduleGenerateBtn.setText("Please wait");
//				scheduleGenerateBtn.setEnabled(false);
//				ScheduleService scheduleService = new ScheduleService();
//				scheduleService.generateSchedule();
//				scheduleGenerateBtn.setEnabled(true);
//				JOptionPane.showMessageDialog(AdminHomePagePanel.this,
//						"Schedule Generated");
//				
//				scheduleGenerateBtn.setText("Schedule Generate");
//				scheduleGenerateBtn.setEnabled(true);
//				
//				ScheduleTablePanel.loadData();

				
				MainFrame.showCard("generateschedulepanel");

			}
		});

		viewScheduleBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				
				MainFrame.showCard("readschedulepanel");

			}
		});
		
		testScheduleBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				
				MainFrame.showCard("testschedulepanel");

			}
		});

		btnPnl.add(courseBtn);
		btnPnl.add(facultyBtn);
		btnPnl.add(degreeBtn);
		btnPnl.add(importDataBtn);
		btnPnl.add(scheduleGenerateBtn);
		btnPnl.add(viewScheduleBtn);
		btnPnl.add(testScheduleBtn);


		mainPanel.add(topPnl, BorderLayout.NORTH);
		mainPanel.add(btnPnl, BorderLayout.CENTER);

		JLabel label = new JLabel("Home Page");
		label.setFont(new Font("Serif", Font.PLAIN, 24));

		topPnl.add(label);

		add(mainPanel, BorderLayout.CENTER);
		btnPnl.setBackground(Color.WHITE);
		mainPanel.setBackground(Color.WHITE);

		setBackground(Color.WHITE);
	}
}
