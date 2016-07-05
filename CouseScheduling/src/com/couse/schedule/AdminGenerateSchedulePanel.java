package com.couse.schedule;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import com.couse.schedule.service.ScheduleService;

public class AdminGenerateSchedulePanel extends Panel {

	public AdminGenerateSchedulePanel() {

		init();
	}

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

		JButton backBtn = new JButton("Back");
		backBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {

				MainFrame.showCard("adminhomepage");
			}
		});

		horizontalPanel.add(backBtn);
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

		JPanel mainPanel = new JPanel(new BorderLayout());
		mainPanel.setPreferredSize(new Dimension(400, 500));

		JPanel topPnl = new JPanel();

		JPanel btnPnl = new JPanel(new FlowLayout(FlowLayout.CENTER));

		btnPnl.setBorder(BorderFactory.createEmptyBorder(50, 10, 10, 10));

		final DefaultComboBoxModel semscombobox = new DefaultComboBoxModel();

		semscombobox.addElement("SPRING");
		semscombobox.addElement("SUMMER");
		semscombobox.addElement("FALL");

		final DefaultComboBoxModel yearcombobox = new DefaultComboBoxModel();

		yearcombobox.addElement("2015");
		yearcombobox.addElement("2016");

		JComboBox jsemscombobox = new JComboBox(semscombobox);
		JComboBox jyearcombobox = new JComboBox(yearcombobox);

		// mainPanel.setBorder(BorderFactory.createEmptyBorder(100, 10, 10,
		// 10));

		mainPanel.add(topPnl, BorderLayout.CENTER);

		topPnl.add(jsemscombobox);
		topPnl.add(jyearcombobox);
		mainPanel.add(topPnl, BorderLayout.NORTH);

		btnPnl.setBackground(Color.WHITE);
		mainPanel.setBackground(Color.WHITE);

		rootPanel.add(mainPanel);

		JButton generateBtn = new JButton("Generate");
		generateBtn.setPreferredSize(new Dimension(100, 30));

		generateBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {

				ScheduleService scheduleService = new ScheduleService();

				System.out.println();
				System.out.println((String) yearcombobox.getSelectedItem());
				String sem = (String) semscombobox.getSelectedItem();

				if (sem.equals("SPRING")) {

					sem = "SP";
				} else if (sem.equals("SUMMER")) {
					sem = "SU";

				} else  {
					sem = "FA";

				}
				
				System.out.println(sem);

				scheduleService.generateIndividualSchedule(sem, Integer
						.parseInt((String) yearcombobox.getSelectedItem()));
				
				ScheduleTablePanel.loadData("refresh");
			}
		});

		topPnl.add(generateBtn, BorderLayout.CENTER);

		add(rootPanel, BorderLayout.CENTER);

		topPnl.setBackground(Color.WHITE);

		setBackground(Color.WHITE);

	}

}
