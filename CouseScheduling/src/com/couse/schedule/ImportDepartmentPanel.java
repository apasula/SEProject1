package com.couse.schedule;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
import com.course.schedule.db.DepartmentDB;
import com.couse.schedule.service.ReadCourseFile;
import com.couse.schedule.service.ReadDepatmentFile;

public class ImportDepartmentPanel extends JPanel {
	private final DefaultTableModel tableModel = new DefaultTableModel();

	public ImportDepartmentPanel() {

		init();
	}

	public void loadData() {
		new SwingWorker<Void, Void>() {
			@Override
			protected Void doInBackground() throws Exception {
				DepartmentDB departmentDB = new DepartmentDB();
				departmentDB.getCoursePaticulars(tableModel);
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
		JLabel fileChooserLabel = new JLabel("Choose department file");
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
							ReadDepatmentFile readDepatmentFile = new ReadDepatmentFile();
							readDepatmentFile.addDepartmentDetails(fileField
									.getText().trim());

							uploadButton.setText("Upload");
							uploadButton.setEnabled(true);
							loadData();
							JOptionPane
									.showMessageDialog(
											ImportDepartmentPanel.this,
											"Data Inserted");

							return null;
						}
					};

					mySwingWorker.execute();

				} else {
					JOptionPane.showMessageDialog(ImportDepartmentPanel.this,
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
