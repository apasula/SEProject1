package com.couse.schedule;

import java.awt.BorderLayout;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import com.couse.schedule.service.Constants;

public class ReadSchedulePanel extends JPanel {
	private JTabbedPane tabbedPane;

	public ReadSchedulePanel() {

		init();
	}
	public static JButton backBtn;

	public void init() {

		JPanel rootPanel = new JPanel();
		rootPanel.setBackground(Color.WHITE);

		rootPanel.setPreferredSize(new Dimension(750, 400));

		BoxLayout box = new BoxLayout(rootPanel, BoxLayout.Y_AXIS);
		rootPanel.setLayout(box);
		add(rootPanel);

		JPanel horizontalPanel = new JPanel();
		horizontalPanel.setBackground(Color.WHITE);

		BoxLayout hbox = new BoxLayout(horizontalPanel, BoxLayout.X_AXIS);
		horizontalPanel.setLayout(hbox);

		 backBtn = new JButton("Back");
		
		
		
		backBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {

				MainFrame.showCard("adminhomepage");
			}
		});

		System.out.println(Constants.loggedinUser);
		if(Constants.loggedinUser==0){
			horizontalPanel.add(backBtn);

		}
		
		horizontalPanel.add(Box.createRigidArea(new Dimension(600, 0)));

		rootPanel.add(horizontalPanel);

		JButton logout = new JButton("Logout");

		logout.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {

				MainFrame.showCard("logoutpanel");
			}
		});
		logout.setAlignmentX(Component.RIGHT_ALIGNMENT);
		horizontalPanel.add(logout);

		setBackground(Color.WHITE);

		tabbedPane = new JTabbedPane();
		final ScheduleTablePanel scheduleTablePanel = new ScheduleTablePanel();
		tabbedPane.addTab("Schedule", new ScheduleTablePanel());

		rootPanel.add(tabbedPane, BorderLayout.CENTER);

		JButton downloadBtn = new JButton("Download");

		downloadBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {

				scheduleTablePanel.print();
			}
		});
		logout.setAlignmentX(Component.RIGHT_ALIGNMENT);
		rootPanel.add(Box.createRigidArea(new Dimension(0, 10)));

		rootPanel.add(downloadBtn);

	}
}
