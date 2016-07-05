package com.couse.schedule;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingWorker;
import javax.swing.table.DefaultTableModel;

import com.course.schedule.db.SectionDB;
import com.couse.schedule.service.Constants;

public class TestSchedulePanel extends JPanel {

	private static final DefaultTableModel tableModel = new DefaultTableModel();
	private JTable jtable;

	public TestSchedulePanel() {

		init();
	}
	public static JButton backBtn;

	
	public static void loadData() {
		new SwingWorker<Void, Void>() {
			@Override
			protected Void doInBackground() throws Exception {
				SectionDB sectionDB = new SectionDB();
				sectionDB.getEnrollmentPerc(tableModel);
				return null;
			}
		}.execute();
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


//		JPanel rootPanel = new JPanel();
//		rootPanel.setPreferredSize(new Dimension(750, 400));
//
//		BoxLayout box = new BoxLayout(rootPanel, BoxLayout.Y_AXIS);
//		rootPanel.setLayout(box);
//		add(rootPanel);

		
		jtable = new JTable(tableModel);
		rootPanel.add(new JScrollPane(jtable));

		loadData();
	}
}
