package com.couse.schedule;

import java.awt.CardLayout;
import java.awt.Color;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

public class MainFrame extends JFrame {

	public static void main(String[] args) {

		for (LookAndFeelInfo lookandfeel : UIManager.getInstalledLookAndFeels()) {
			if ("Nimbus".equals(lookandfeel.getName())) {
				try {
					UIManager.setLookAndFeel(lookandfeel.getClassName());
				} catch (Exception e) {
					e.printStackTrace();
				}
				break;
			}
		}
		MainFrame main = new MainFrame();
		main.init();
	}
	
	static AdminGenerateSchedulePanel adminGenerateSchedulePanel;

	public void addPanelstoCard(JPanel cards) {
		AdminLogin adminLogin = new AdminLogin();
		DirectorLogin directorLogin = new DirectorLogin();
		LandingPanel landingPanel = new LandingPanel();
		LogoutPanel logoutPanel = new LogoutPanel();
		AdminImportDataPanel adminImportDataPanel = new AdminImportDataPanel();
		DirectorHomePanel directorHomePanel = new DirectorHomePanel();
		LogoutPanel logPanel = new LogoutPanel();
		AdminHomePagePanel adminHomePagePanel = new AdminHomePagePanel();
		 adminGenerateSchedulePanel = new AdminGenerateSchedulePanel();

		AdminCoursePanel adminCoursePanel = new AdminCoursePanel();
		AdminDegreePanel adminDegreePanel = new AdminDegreePanel();
		AdminFacultyPanel adminFacultyPanel = new AdminFacultyPanel();
		ReadSchedulePanel readSchedulePanel = new ReadSchedulePanel();
		TestSchedulePanel testSchedulePanel = new TestSchedulePanel();


		cards.add(adminLogin, "adminlogin");
		cards.add(landingPanel, "landingpanel");
		cards.add(directorLogin, "directorlogin");
		cards.add(logoutPanel, "logoutpanels");
		cards.add(adminImportDataPanel, "adminimportdata");
		cards.add(directorHomePanel, "directorhomepanel");
		cards.add(logPanel, "logoutpanel");
		cards.add(adminHomePagePanel, "adminhomepage");
		cards.add(adminCoursePanel, "admincoursepanel");
		cards.add(adminDegreePanel, "admindegreepanel");
		cards.add(adminFacultyPanel, "adminfacultypanel");
		cards.add(readSchedulePanel, "readschedulepanel");
		cards.add(adminGenerateSchedulePanel, "generateschedulepanel");
		cards.add(testSchedulePanel, "testschedulepanel");


	}

	public static void showCard(String panelname) {
		CardLayout cl = (CardLayout) (cards.getLayout());
		
		if(panelname.equals("generateschedulepanel")){
		   cards.remove(adminGenerateSchedulePanel);
		   adminGenerateSchedulePanel = new AdminGenerateSchedulePanel();
			cards.add(adminGenerateSchedulePanel, "generateschedulepanel");

		}
		
		cl.show(cards, panelname);

	}

	public static JPanel cards;

	public void init() {

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(800, 480);
		setLocation(200, 200);
		setTitle("Course Scheduling");

		JPanel rootPanel = new JPanel();

		BoxLayout box = new BoxLayout(rootPanel, BoxLayout.Y_AXIS);
		rootPanel.setLayout(box);

		cards = new JPanel(new CardLayout());

		addPanelstoCard(cards);

		rootPanel.add(cards);
		add(rootPanel);

		showCard("landingpanel");
		getContentPane().setBackground(Color.WHITE);

		setVisible(true);
	}
}
