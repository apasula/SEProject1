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

import com.couse.schedule.model.User;
import com.couse.schedule.service.Constants;
import com.couse.schedule.service.UserLogin;

public class DirectorLogin extends JPanel {

	public DirectorLogin() {
		init();
	}

	public void init() {
		setLayout(null);

		JLabel hint = new JLabel("Director Login");
		hint.setForeground(Color.blue);
		hint.setFont(new Font("Serif", Font.BOLD, 20));

		JLabel usernamelabel = new JLabel("Enter Username:");
		JLabel passwordlabel = new JLabel("Enter Password:");
		final JTextField usernametf = new JTextField();
		final JPasswordField passwordtf = new JPasswordField();
		JButton submitbtn = new JButton("Submit");

		submitbtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				UserLogin login = new UserLogin();
				User user = new User();
				
				boolean result = login.directorLogin(usernametf.getText().toString().trim(), passwordtf.getText().toString().trim());
				if (result) {
					Constants.loggedinUser = 1;
					ReadSchedulePanel.backBtn.hide();
					MainFrame.showCard("directorhomepanel");
				} else {
					JOptionPane.showMessageDialog(DirectorLogin.this,"Invalid Credentials");
				}
			}
		});
		hint.setBounds(350, 30, 400, 30);
		usernamelabel.setBounds(250, 70, 200, 30);
		passwordlabel.setBounds(250, 110, 200, 30);
		usernametf.setBounds(350, 70, 200, 30);
		passwordtf.setBounds(350, 110, 200, 30);
		submitbtn.setBounds(350, 160, 100, 30);

		add(hint);
		add(usernamelabel);
		add(usernametf);
		add(passwordlabel);
		add(passwordtf);
		add(submitbtn);

		setBackground(Color.WHITE);

	}
}
