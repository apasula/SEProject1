package com.couse.schedule;

import java.awt.FlowLayout;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingWorker;
import javax.swing.table.DefaultTableModel;

import com.course.schedule.db.CourseDB;
import com.course.schedule.db.StudentDB;
import com.couse.schedule.model.Student;
import com.couse.schedule.service.ReadCSVFile;
import com.couse.schedule.service.ReadCourseFile;

public class ImportCoursesPanel extends Panel {

	private final DefaultTableModel tableModel = new DefaultTableModel();

	public ImportCoursesPanel() {

		init();
	}

	public void loadData() {
		new SwingWorker<Void, Void>() {
			@Override
			protected Void doInBackground() throws Exception {
				CourseDB courseDB = new CourseDB();
				courseDB.getCourses(tableModel);
				return null;
			}
		}.execute();
	}

	public void init() {
		JPanel rootPanel = new JPanel();
		BoxLayout box = new BoxLayout(rootPanel, BoxLayout.Y_AXIS);
		rootPanel.setLayout(box);
		add(rootPanel);

		JPanel fileChooserPanel = new JPanel();
		FlowLayout layout = new FlowLayout(1, 5, 5);
		fileChooserPanel.setLayout(layout);
		JLabel fileChooserLabel = new JLabel("Choose course file");
		final JTextField fileField = new JTextField();
		fileField.setSize(100, 10);
		fileField.setColumns(35);
		fileChooserPanel.add(fileChooserLabel);
		fileChooserPanel.add(fileField);
		JButton browseButton = new JButton("Browse");
		browseButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent event) {
				JFileChooser fileChooser = new JFileChooser();

				int value = fileChooser.showOpenDialog(null);
				if (value == JFileChooser.APPROVE_OPTION) {
					fileField.setText(fileChooser.getSelectedFile().toString());
				}
			}
		});
		final JButton uploadButton = new JButton("Upload");

		uploadButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent event) {
				// TODO Auto-generated method stub
				if (!fileField.getText().trim().equals("")) {

					SwingWorker<Void, Void> mySwingWorker = new SwingWorker<Void, Void>() {
						@Override
						protected Void doInBackground() throws Exception {

							uploadButton.setText("Uploading");
							uploadButton.setEnabled(false);
							ReadCourseFile readCourseFile = new ReadCourseFile();
							readCourseFile.addCourseDetails(fileField.getText()
									.trim());

							uploadButton.setText("Upload");
							uploadButton.setEnabled(true);
							loadData();
							JOptionPane.showMessageDialog(
									ImportCoursesPanel.this, "Data Inserted");

							return null;
						}
					};

					mySwingWorker.execute();

				} else {
					JOptionPane.showMessageDialog(ImportCoursesPanel.this,
							"Please choose file");

				}
			}
		});
		JButton cancelButton = new JButton("Cancel");
		cancelButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent event) {
				// TODO Auto-generated method stub
				fileField.setText("");
			}
		});

		fileChooserPanel.add(browseButton);
		fileChooserPanel.add(uploadButton);
		fileChooserPanel.add(cancelButton);
		rootPanel.add(fileChooserPanel);

		JTable jtable = new JTable(tableModel);
		rootPanel.add(new JScrollPane(jtable));

		loadData();
	}
}
